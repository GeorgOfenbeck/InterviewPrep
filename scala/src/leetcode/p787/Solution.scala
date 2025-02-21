package leetcode.p787

import scala.collection.{mutable => mu}

case class Edge(to: Int, price: Int)
case class GraphNode(val id: Int, val edges: Set[Edge])

case class QueueItem(dist: Int, id: Int, stops: Int)

object Solution {
  def findCheapestPrice(
      n: Int,
      flights: Array[Array[Int]],
      src: Int,
      dst: Int,
      k: Int
  ): Int = {
    val flightMap = flights.groupMap(f => f(0))(f => (Edge(f(1), f(2))))

    val stops = Array.fill(n)(Int.MaxValue)

    val queue: mu.PriorityQueue[QueueItem] = mu.PriorityQueue.empty[QueueItem](
      Ordering.by[QueueItem, Int](x => x.dist).reverse
    )

    queue.enqueue(QueueItem(0, src, 0))

    while (queue.nonEmpty) {
      val item = queue.dequeue()
      val (curDist, curId, curStops) = (item.dist, item.id, item.stops)

      // We have already encountered a path with a lower cost and fewer stops,
      // or the number of stops exceeds the limit.
      if (curStops > stops(curId) || curStops > k + 1) {
//skip
      } else {
        stops(curId) = curStops
        if (curId == dst) {
          return curDist
        }

        flightMap.get(curId) match {
          case None =>
          case Some(value) => {
            value.foreach { edge =>
              queue.enqueue(
                QueueItem(curDist + edge.price, edge.to, curStops + 1)
              )
            }
          }
        }
      }
    }

    // val graph = toGraph(flights)
    // val (dist, path) = dijkstra(src, dst, k, graph)
    // toGraphVizDotFile("graph.dot", graph, path)
    return -1
  }

  def dijkstra(
      src: Int,
      dst: Int,
      k: Int,
      graph: Map[Int, GraphNode]
  ): (Int, Map[Int, Int]) = {

    // price, id, stops
    val asc: Ordering[(Int, Int, Int)] =
      Ordering.by[(Int, Int, Int), Int](x => (x._1))
    val queue = mu.PriorityQueue.empty[(Int, Int, Int)](asc.reverse)

    val visited: mu.Set[Int] = mu.Set.empty[Int]

    // current shortest path to id
    val state = mu.Map.empty[Int, Int]
    for (id <- graph.keys) {
      state.update(id, Int.MaxValue)
    }

    // parent of id in shortest path (id -> parent)
    val path = mu.Map.empty[Int, Int]

    visited.add(src)

    queue.enqueue((0, src, k + 1))

    var found = false
    var res = -1
    while (queue.nonEmpty && !found) {
      val (curcost, cur, stops) = queue.dequeue()
      visited.add(cur)
      if (cur == dst) {
        found = true
        res = curcost
      } else {
        val neighs = graph(cur).edges
        for (neigh <- neighs) {
          if (visited.contains(neigh.to) || stops == 0) {
            // skip visited
          } else {
            val costFromCur = curcost + neigh.price
            val sofarCost = state(neigh.to)
            if (costFromCur < sofarCost) {
              state.update(neigh.to, costFromCur)
              path.update(neigh.to, cur)
              queue.enqueue((costFromCur, neigh.to, stops - 1))
            }
          }
        }
      }

    }
    return (res, path.toMap)
  }

  def toGraph(flights: Array[Array[Int]]): Map[Int, GraphNode] = {
    val mumap = mu.Map.empty[Int, GraphNode]

    flights.foreach { edge =>
      val (from, to, price) = (edge(0), edge(1), edge(2))
      mumap.updateWith(from)(opt =>
        opt match {
          case None => Some(GraphNode(from, Set(Edge(to, price))))
          case Some(value) => {
            Some(GraphNode(from, value.edges + Edge(to, price)))
          }
        }
      )
      mumap.updateWith(to)(opt =>
        opt match {
          case None        => Some(GraphNode(to, Set.empty))
          case Some(value) => Some(value)
        }
      )
    }
    mumap.toMap
  }

  import java.io.PrintWriter

  def toGraphVizDotFile(
      fileName: String,
      graph: Map[Int, GraphNode],
      path: Map[Int, Int]
  ): Unit = {
    val file = new java.io.File(fileName)
    val sb = new StringBuilder
    sb.append("digraph G {\n")
    graph.foreach { case (from, node) =>
      node.edges.foreach { edge =>
        sb.append(s"  $from -> ${edge.to} [label=${edge.price}];\n")
      }
    }

    path.foreach { case (from, to) =>
      sb.append(
        s"  $from -> ${to} [style=dotted];\n"
      )
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

  @main
  def test(): Unit = {
    test3()
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
        1
      )
    ) // 700
  }

  def test2(): Unit = {
    // n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
    println(
      findCheapestPrice(
        3,
        Array(
          Array(0, 1, 100),
          Array(1, 2, 100),
          Array(0, 2, 500)
        ),
        0,
        2,
        1
      )
    ) // 200
  }

  def test3(): Unit = {
    // [[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]], 0, 2,2
    println(
      findCheapestPrice(
        5,
        Array(
          Array(0, 1, 5),
          Array(1, 2, 5),
          Array(0, 3, 2),
          Array(3, 1, 2),
          Array(1, 4, 1),
          Array(4, 2, 1)
        ),
        0,
        2,
        2
      )
    ) // 7
  }

}
