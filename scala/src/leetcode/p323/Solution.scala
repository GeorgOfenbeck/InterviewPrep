package leetcode.p323

import scala.annotation.tailrec
import scala.collection.{mutable => mu}

case class Node(id: Int, neigh: Set[Int])

object Solution {
  def countComponents(n: Int, edges: Array[Array[Int]]): Int = {
    val graph = createGraph(edges, n)
    var nodes = (0 until n).toSet
    var count = 0

    var visited = Set.empty[Int]
    while (nodes.nonEmpty) {
      val node = nodes.head
      visited = dfs(node, visited, graph)
      nodes = nodes -- visited
      count = count + 1
    }
    count
  }

  def dfs(cur: Int, visited: Set[Int], graph: Map[Int, Node]): (Set[Int]) = {
    if (visited.contains(cur)) return visited
    val neigh = graph(cur).neigh
    neigh.foldLeft(visited + cur) { (acc, ele) => dfs(ele, acc, graph) }
  }

  def createGraph(edges: Array[Array[Int]], n: Int): Map[Int, Node] = {
    val emptyGraph = (0 until n)
      .map(Node(_, Set.empty))
      .toSet
      .map(node => node.id -> node)
      .toMap
    edges
      .foldLeft(emptyGraph) { (acc, edge) =>
        val (u, v) = (edge(0), edge(1))
        val nodeU = acc.getOrElse(u, Node(u, Set.empty))
        val nodeV = acc.getOrElse(v, Node(v, Set.empty))
        acc + (u -> nodeU.copy(neigh = nodeU.neigh + v)) + (v -> nodeV
          .copy(neigh = nodeV.neigh + u))
      }
  }
}
