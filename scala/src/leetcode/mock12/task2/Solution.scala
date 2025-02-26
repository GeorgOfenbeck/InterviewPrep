package leetcode.mock12.task2

import scala.collection.{mutable => mu}

case class Edge(val to: Int, weight: Int)
case class Node(val id: Int, to: Vector[Edge])

object Solution {
  def networkDelayTime(times: Array[Array[Int]], n: Int, k: Int): Int = {
    val graph = toGraph(times, n)
    dijkstra(graph, k, n)
  }

  def dijkstra(graph: Map[Int, Node], k: Int, n: Int): Int = {

    val queue =
      mu.PriorityQueue.empty[(Int, Int)](Ordering.by[(Int, Int), Int](-_._2))

    val state = mu.Map.empty[Int, Int]
    for (i <- 1 to n) {
      state.addOne((i, Int.MaxValue))
    }

    state(k) = 0
    queue.enqueue((k, 0))

    while (queue.nonEmpty) {
      val (curidx, curcost) = queue.dequeue()
      val cur = graph(curidx)
      for (neigh <- cur.to) {
        val newcost = curcost + neigh.weight
        // we found a better path
        if (state(neigh.to) > newcost) {
          state(neigh.to) = newcost
          queue.enqueue((neigh.to, newcost))
        }
      }
    }

    if (state.values.filter(x => x == Int.MaxValue).size > 0) {
      return -1
    } else {
      state.values.max
    }
  }

  def toGraph(times: Array[Array[Int]], n: Int): Map[Int, Node] = {
    val mmap = mu.Map.empty[Int, Node]

    for (i <- 1 to n) {
      mmap.addOne(i, Node(i, Vector.empty))
    }

    for (edge <- times) {
      val (from, to, weight) = (edge(0), edge(1), edge(2))
      mmap.updateWith(from)(opt =>
        opt match {
          case None        => Some(Node(from, Vector(Edge(to, weight))))
          case Some(value) => Some(Node(from, value.to :+ Edge(to, weight)))
        }
      )
    }
    mmap.toMap
  }
}
