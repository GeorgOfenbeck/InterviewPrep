/*
package leetcode.p787wrong

import scala.collection.{mutable => mu}

case class Edge(to: Int, price: Int)
case class Node(id: Int, to: Set[Edge])

case class DagNode(id: Int, to: Set[Edge], incoming: Set[Edge])

object Solution {
  def findCheapestPrice(
      n: Int,
      flights: Array[Array[Int]],
      src: Int,
      dst: Int,
      k: Int
  ): Int = {
    val graph = toGraph(flights)
    toGraphVizDotFile("graph.dot", graph)
    val dag = toDag(graph, k)
    DagtoGraphVizDotFile("dag.dot", dag)
    if (!dag.contains(dst))
      return -1
    val purgedDag = purgeDag(dag, dst, k)
    DagtoGraphVizDotFile("purgedDag.dot", purgedDag)
    3
  }

  def findBest(
      id: Int,
      src: Int,
      dag: Map[Int, DagNode],
      maxk: Int,
      k: Int,
      cache: mu.Map[(Int, Int), Int]
  ): Int = {

    if (cache.contains((id, maxk))) {
      return cache((id, maxk))
    }
    if (maxk == k) {
      if (src != id) {
        cache.updateWith(id, maxk)(opt =>
          opt match {
            case Some(value) => Some(value)
            case None        => Some(Int.MaxValue)
          }
        )
      } else {}
    } else {}

    val node = dag(id)

    val res =
      node.to.map(target => findBest(target.to, dag, maxk - 1, cache)).min

    cache((id, maxk)) = res
    res
  }

  def purgeDag(dag: Map[Int, DagNode], dst: Int, k: Int): Map[Int, DagNode] = {
    val visited = mu.Set.empty[Int]

    // (id,dist)
    val queue: mu.PriorityQueue[(Int, Int)] =
      mu.PriorityQueue.empty(Ordering.by[(Int, Int), Int](_._2))

    queue.enqueue((dst, 0))
    while (queue.nonEmpty) {
      val (id, dist) = queue.dequeue()

      if (visited.contains(id)) {
        // skip
      } else {
        visited.add(id)
        val node = dag(id)
        node.incoming.foreach { edge =>
          val newDist = dist + 1
          if (newDist - 1 <= k)
            queue.enqueue((edge.to, newDist))
        }
      }
    }
    dag.filter((key, value) => visited.contains(key))
  }

  def toDag(graph: Map[Int, Node], k: Int): Map[Int, DagNode] = {
    val dag = mu.Map.empty[Int, DagNode]

    val visited = mu.Set.empty[Int]
    // priorityQueue - asc #price

    // ( id, dist)
    val queue: mu.PriorityQueue[(Int, Int)] =
      mu.PriorityQueue.empty(Ordering.by[(Int, Int), Int](_._2))

    queue.enqueue((0, 0))
    dag.addOne(0, dag.getOrElse(0, DagNode(0, graph(0).to, Set())))
    val reverseEdge: mu.Map[Int, Set[Edge]] = mu.Map.empty
    while (queue.nonEmpty) {
      val (id, dist) = queue.dequeue()
      if (visited.contains(id)) {
        // skip
      } else {

        visited.add(id)
        val node = graph(id)
        node.to.foreach { edge =>
          val to = edge.to
          val price = edge.price

          reverseEdge(to) = reverseEdge.getOrElse(to, Set()) + Edge(id, -price)
          val newDist = dist + 1 // hops
          if (newDist > k) {
            if (newDist == k + 1) {
              val graphNode = graph(to)
              val dagNode: DagNode =
                dag.getOrElse(to, DagNode(to, graphNode.to, Set()))
              dag.addOne(to, dagNode)
            }
            // skip
          } else {
            val graphNode = graph(to)
            val dagNode: DagNode =
              dag.getOrElse(to, DagNode(to, graphNode.to, Set()))
            dag.addOne(to, dagNode)
            queue.enqueue((to, newDist))
          }
        }
      }
    }
    reverseEdge.foreach { case (to, froms) =>
      if (dag.contains(to)) {
        val node = dag(to)
        dag(to) = node.copy(incoming = froms)
      }
    }
    dag.toMap
  }

  import java.io.PrintWriter

  def toGraphVizDotFile(fileName: String, graph: Map[Int, Node]): Unit = {
    val file = new java.io.File(fileName)
    val sb = new StringBuilder
    sb.append("digraph G {\n")
    graph.foreach { case (from, node) =>
      node.to.foreach { edge =>
        sb.append(s"  $from -> ${edge.to} [label=${edge.price}];\n")
      }
    }
    sb.append("}")
    val str = sb.toString()
    val writer = new PrintWriter(file)
    try {
      writer.write(str)
    } finally {
      writer.close()
    }
  }

  def DagtoGraphVizDotFile(fileName: String, dag: Map[Int, DagNode]): Unit = {
    val file = new java.io.File(fileName)
    val sb = new StringBuilder
    sb.append("digraph G {\n")
    dag.foreach { case (from, node) =>
      node.to.foreach { edge =>
        sb.append(s"  $from -> ${edge.to} [label=${edge.price}];\n")
      }

      node.incoming.foreach { edge =>
        sb.append(s"  $from -> ${edge.to} [label=${edge.price}];\n")
      }

    }
    sb.append("}")
    val str = sb.toString()
    val writer = new PrintWriter(file)
    try {
      writer.write(str)
    } finally {
      writer.close()
    }
  }
  def toGraph(flights: Array[Array[Int]]): Map[Int, Node] = {
    val graph = mu.Map[Int, Node]()
    flights.foreach { case Array(from, to, price) =>
      val edge = Edge(to, price)
      val node = graph.getOrElse(from, Node(from, Set()))
      graph(from) = node.copy(to = node.to + edge)
      val targetNode = graph.getOrElse(to, Node(to, Set()))
      graph(to) = targetNode
    }
    graph.toMap
  }

  @main
  def test(): Unit = {
    test1()
  }
  def test1(): Unit = {
    // n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
    println(
      findCheapestPrice(
        4,
        Array(
          Array(0, 1, 100),
          Array(1, 2, 100),
          Array(2, 0, 100),
          Array(1, 3, 600),
          Array(2, 3, 200)
        ),
        0,
        3,
        0
      )
    ) // 700
  }
}*/
