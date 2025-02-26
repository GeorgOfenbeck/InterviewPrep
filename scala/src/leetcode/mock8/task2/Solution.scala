package leetcode.mock8.task2

import scala.collection.{mutable => mu}

object Solution {

  case class Graph(id: Int, neighbors: Set[Int], var color: Option[Int] = None)

  def gardenNoAdj(n: Int, paths: Array[Array[Int]]): Array[Int] = {
    val graph = paths2Graph(paths, n)

    graph.map { (id, node) =>
      val colors = Set(1, 2, 3, 4)
      val neighbors = node.neighbors
      val neighborColors = neighbors.map(n => graph(n).color).flatten
      val color = (colors -- neighborColors).head
      node.color = Some(color)
    }
    val res = for (i <- 1 to n) yield graph(i).color.get
    res.toArray
  }

  def paths2Graph(paths: Array[Array[Int]], n: Int): Map[Int, Graph] = {
    val map = mu.Map.empty[Int, Graph]
    for (i <- 1 to n) {
      map.update(i, Graph(i, Set.empty))
    }
    for (edge <- paths) {
      val (from, to) = (edge(0), edge(1))

      map.updateWith(from)(_ match {
        case None =>
          Some(Graph(from, Set(to)))
        case Some(graph) =>
          Some(graph.copy(neighbors = graph.neighbors + to))
      })
      map.updateWith(to)(_ match {
        case None =>
          Some(Graph(to, Set(from)))
        case Some(graph) =>
          Some(graph.copy(neighbors = graph.neighbors + from))
      })

    }
    map.toMap
  }
}
