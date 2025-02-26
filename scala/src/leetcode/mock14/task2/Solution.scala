package leetcode.mock14.task2

import scala.collection.{mutable => mu}

case class Node(id: Int, to: Set[Int], from: Set[Int])

object Solution {
  def minReorder(n: Int, connections: Array[Array[Int]]): Int = {
    val graph = toGraph(connections)

    val visited = mu.Set.empty[Int]

    visited.add(0)

    rec(graph, visited, graph(0))
  }

  def rec(graph: Map[Int, Node], visited: mu.Set[Int], node: Node): Int = {
    var res = 0
    for (to <- node.to) {
      if (!visited.contains(to)) {
        visited.add(to)
        res += 1
        res += rec(graph, visited, graph(to))
      }
    }
    for (from <- node.from) {
      if (!visited.contains(from)) {
        visited.add(from)
        res += rec(graph, visited, graph(from))
      }
    }
    res
  }

  def toGraph(connections: Array[Array[Int]]): Map[Int, Node] = {
    val mmap = mu.Map.empty[Int, Node]

    for (edge <- connections) {
      val (from, to) = (edge(0), edge(1))

      mmap.updateWith(from)(opt =>
        opt match {
          case Some(node) => Some(node.copy(to = node.to + to))
          case None       => Some(Node(from, Set(to), Set.empty))
        }
      )
      mmap.updateWith(to)(opt =>
        opt match {
          case Some(node) => Some(node.copy(from = node.from + from))
          case None       => Some(Node(to, Set.empty, Set(from)))
        }
      )
    }
    mmap.toMap
  }
}
