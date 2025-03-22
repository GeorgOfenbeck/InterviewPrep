package leetcode.p1334

import scala.collection.{mutable => mu}

case class City(id: Int, neighbors: mu.Map[Int, Int]) {
  def addNeighbor(city: City, distance: Int): Unit = {
    neighbors.put(city.id, distance)
  }
}

object Solution {
  def findTheCity(
      n: Int,
      edges: Array[Array[Int]],
      distanceThreshold: Int
  ): Int = {

    val graph = mu.Map[Int, City]()
    for (i <- 0 until n) {
      graph.put(i, City(i, mu.Map()))
    }

    for (edge <- edges) {
      val (from, to, distance) = (edge(0), edge(1), edge(2))
      graph(from).addNeighbor(graph(to), distance)
      graph(to).addNeighbor(graph(from), distance)
    }

    val res = (0 until n)
      .map(id => (reachable(distanceThreshold, graph, id).size, id))
    println(res)

    res.minBy { case (a, b) => (a, -b) }._2

  }

  def reachable(
      distanceThreshold: Int,
      graph: mu.Map[Int, City],
      n: Int
  ): Set[Int] = {

    val ordering = Ordering.by[(Int, Int), Int](_._2)
    val queue = mu.PriorityQueue.empty[(Int, Int)](ordering.reverse)

    val visited = mu.Set[Int]()
    queue.enqueue((n, 0))

    while (queue.nonEmpty) {
      val (city, distance) = queue.dequeue()
      if (visited.contains(city)) {
        // skip
      } else {
        visited.add(city)
        for ((neighbor, neighborDistance) <- graph(city).neighbors) {
          val newDistance = distance + neighborDistance
          if (newDistance <= distanceThreshold) {
            queue.enqueue((neighbor, newDistance))
          }
        }
      }
    }
    visited.toSet
  }
}
