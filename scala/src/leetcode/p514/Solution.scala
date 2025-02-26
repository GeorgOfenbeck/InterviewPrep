package leetcode.p514

import scala.collection.{mutable => mu}

object Solution {
  def findRotateSteps(ring: String, key: String): Int = {
    toDistanceMap(ring, key)

  }

  case class RingChar(char: Char, pos: Int)

  case class GraphNode(
      value: RingChar,
      depth: Int,
      children: Map[GraphNode, Int]
  )

  def dijkstra(graph: GraphNode, endDepth: Int): Int = {

    val ordering = Ordering.by[(GraphNode, Int), Int](x => x._2).reverse
    val queue =
      mu.PriorityQueue.empty[(GraphNode, Int)](ordering)

    val cost = mu.Map.empty[(RingChar, Int), Int]

    queue.enqueue((graph, 0))

    while (queue.nonEmpty) {
      val (cur, curcost) = queue.dequeue()

      if (cur.depth == endDepth) return curcost
      cur.children.foreach { case (child, distance) =>
        val (ckey) = (child.value, child.depth)
        val newCost = curcost + distance
        if (!cost.contains(ckey) || newCost < cost(ckey)) {
          cost(ckey) = newCost
          queue.enqueue((child, newCost))
        }
      }
    }
    -1
  }

  // create a Distance Matrix between all RingChars
  def toDistanceMap(ring: String, key: String): Int = {

    val allRingChars: Set[RingChar] = ring.zipWithIndex.map { case (c, i) =>
      RingChar(c, i)
    }.toSet

    val charPos: Map[Char, Set[RingChar]] = allRingChars.groupBy(_.char)

    val distArray = Array.fill(ring.length, ring.length)(0)

    for (i <- 0 until ring.length) {
      for (j <- 0 until ring.length) {
        val a = RingChar(ring(i), i)
        val b = RingChar(ring(j), j)

        val distance = calcDistance(a, b, ring)
        distArray(i)(j) = distance
      }
    }
    val graph = if (false) {
      keyToGraph(
        RingChar(ring.head, 0),
        key,
        charPos,
        distArray,
        0,
        mu.Map.empty
      )
    } else {
      keyToGraphNonRec(
        RingChar(ring.head, 0),
        key,
        distArray,
        charPos
      )
    }
    // toGraphVizDotFile("fallout.dot", graph)
    dijkstra(graph, key.length - 1)
  }

  def keyToGraphNonRec(
      start: RingChar,
      key: String,
      distArray: Array[Array[Int]],
      charPos: Map[Char, Set[RingChar]]
  ): GraphNode = {

    val layers = mu.Map.empty[Int, Set[GraphNode]]

    for (i <- (0 until key.size).reverse) {

      val curKeyVal = key(i)
      val curPosOpts = charPos(curKeyVal)

      val prevLayer = layers.getOrElse(i + 1, Set.empty)
      for (curPos <- curPosOpts) {

        val dist = prevLayer.toVector.map { prevNode =>
          (prevNode, distArray(prevNode.value.pos)(curPos.pos) + 1)
        }.toMap

        val newNode = GraphNode(curPos, i, dist)
        layers(i) = layers.getOrElse(i, Set.empty) + newNode
      }
    }

    val prevLayer = layers.getOrElse(0, Set.empty)

    val dist = prevLayer.toVector.map { prevNode =>
      (prevNode, distArray(prevNode.value.pos)(start.pos) + 1)
    }.toMap

    // println(s"nr of node: ${layers.map((k, v) => v.size).sum}")

    GraphNode(start, -1, dist)

  }

  def keyToGraph(
      parent: RingChar,
      target: String,
      charPos: Map[Char, Set[RingChar]],
      distArray: Array[Array[Int]],
      depth: Int,
      cache: mu.Map[(RingChar, Int), GraphNode]
  ): GraphNode = {

    println(s"depth $depth, parent ${parent.char}, target $target")
    // if we are the last key we are the leafs of the graph

    if (target.isEmpty || target.length < 5)
      return GraphNode(parent, depth, Map.empty)

    // otherwise get all possilbe children at key+1
    val targetOptions = charPos(target.head)

    val childNodes = targetOptions.map { targetPos =>
      {

        val distance = distArray(parent.pos)(targetPos.pos) + 1
        val sub = cache.get((targetPos, depth + 1)) match {
          case Some(node) => {
            println(
              s"cache hit ${targetPos.char}(${targetPos.pos}-${depth + 1})"
            )
            node
          }
          case None =>
            val subgraph = keyToGraph(
              targetPos,
              target.tail,
              charPos,
              distArray,
              depth + 1,
              cache
            )
            cache((targetPos, depth + 1)) = subgraph
            subgraph
        }
        (sub, distance)
      }
    }.toMap

    GraphNode(parent, depth, childNodes)
  }

  // calculate Distance between two RingChars
  def calcDistance(a: RingChar, b: RingChar, ring: String): Int = {
    val (left, right) = if (a.pos < b.pos) (a, b) else (b, a)

    val antiClockwise = right.pos - left.pos
    val clockwise = left.pos + ring.length - right.pos

    Math.min(antiClockwise, clockwise)
  }

  def toGraphVizDotFile(
      fileName: String,
      graph: GraphNode
  ): Unit = {
    import java.io.PrintWriter
    val file = new java.io.File(fileName)
    val sb = new StringBuilder
    val visited = mu.Set.empty[(RingChar, Int)]
    sb.append("digraph G {\n")
    rec(visited, sb, graph)
    sb.append("}")
//    sb.append(graph.toString())
    val str = sb.toString()
    val writer = new PrintWriter(file)
    try {
      writer.write(str)
    } finally {
      writer.close()
    }
  }

  def rec(
      visited: mu.Set[(RingChar, Int)],
      sb: StringBuilder,
      graph: GraphNode
  ): Unit = {
    if (visited.contains((graph.value, graph.depth))) return

    visited.addOne((graph.value, graph.depth))

    graph.children.foreach { case (child, distance) =>
      sb.append(
        s""""${graph.value.char}(${graph.value.pos}-${graph.depth})" -> "${child.value.char}(${child.value.pos}-${child.depth})" [label=$distance]\n"""
      )
      rec(visited, sb, child)
    }
  }

  @main
  def test(): Unit = {
// ring = "godding", key = "gd"
    // println(findRotateSteps("godding", "gd"))

    println(findRotateSteps("godding", "godding"))
  }

  @main
  def test2(): Unit = {
// ring = "godding", key = "gd"
    // println(findRotateSteps("godding", "gd"))

    // println(findRotateSteps("godding", "godding"))
    println(
      findRotateSteps(
        "caotmcaataijjxi",
        "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"
      )
    )
  }
}
