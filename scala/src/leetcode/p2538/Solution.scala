package leetcode.p2538

/*
 * 0 to n-1 nodes
 * given n, edges ai to bi
 * prices of nodes
 *
 * each node min 1 degree
 *
 * price sum of path - sum of all nodes on path
 * can be rooted on any node
 *
 * cost = max(sum) - min(sum)
 *
 * prices are positive
 * # edges n-1
 * non binary tree <- can root any node
 *
 *
 * min = just root
 *
 * max =>
 */

import scala.collection.{mutable => mu}

case class Node(
    id: Int,
    neighbors: mu.Set[Int],
    price: Int,
    maxSum: mu.Map[Int, Long],
    var max: Long = Long.MinValue
)

object Solution {
  def maxOutput(n: Int, edges: Array[Array[Int]], price: Array[Int]): Long = {
    val graph = toGraph(n, edges, price)
    (0 until n)
      .map(i => {
        val max = maxPath(i, graph, -1)
        val min = graph(i).price
        val cost = max - min
        cost
      })
      .max
  }

  def maxPath(i: Int, graph: mu.Map[Int, Node], without: Int): Long = {
    val cur = graph(i)
    val neighbors =
      cur.neighbors.diff(Set(without)) // exclude where we came from

    if (neighbors.isEmpty) {
      return cur.price
    } else {
      cur.max = neighbors.map { neighbor =>
        val maxSum = cur.maxSum.get(neighbor) match {
          case Some(value) => value
          case None => {
            val nmax = maxPath(neighbor, graph, i)
            cur.maxSum(neighbor) = nmax
            nmax
          }
        }
        maxSum
      }.max + cur.price
      cur.max
    }
  }

  def toGraph(
      n: Int,
      edges: Array[Array[Int]],
      price: Array[Int]
  ): mu.Map[Int, Node] = {
    val graph = (0 until n)
      .map(i => i -> Node(i, mu.Set.empty, price(i), mu.Map.empty))
      .to(mu.Map)
    for (edge <- edges) {
      val (a, b) = (edge(0), edge(1))
      graph(a).neighbors += b
      graph(b).neighbors += a
    }
    graph
  }

  @main
  def test(): Unit = {
    // n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
    println(
      maxOutput(
        6,
        Array(Array(0, 1), Array(1, 2), Array(1, 3), Array(3, 4), Array(3, 5)),
        Array(9, 8, 7, 6, 10, 5)
      )
    )
  }

}
