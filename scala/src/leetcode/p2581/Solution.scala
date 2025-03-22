package leetcode.p2581

import scala.collection.{mutable => mu}

case class Node(id: Int, neighbors: mu.Set[Int], correct: mu.Map[Int, Int])

object Solution {
  def rootCount(
      edges: Array[Array[Int]],
      guesses: Array[Array[Int]],
      k: Int
  ): Int = {

    val graph = toGraph(edges)
    val guessesSet = guesses.map { case Array(a, b) => (a, b) }.toSet
    println(guessesSet)
    val counts = (0 to edges.size)
      .map { i => correctGuesses(i, graph, guessesSet, -1) }
    println(counts)
    val res = counts.count(_ >= k)
    graph.foreach((a, b) => println(b))
    res
  }
  def println(x: Any): Unit = {}

  def correctGuesses(
      id: Int,
      graph: Map[Int, Node],
      guesses: Set[(Int, Int)],
      prev: Int
  ): Int = {
    val downstream = graph(id).neighbors.diff(Set(prev))
    if (downstream.isEmpty) return 0

    val res = downstream.toVector.map { neighbor =>
      val correct = graph(id).correct.get(neighbor) match {
        case Some(value) => value
        case None => {
          val ccorrect = correctGuesses(neighbor, graph, guesses, id)
          val ncorrect =
            if (guesses.contains((id, neighbor))) ccorrect + 1 else ccorrect
          graph(id).correct(neighbor) = ncorrect
          ncorrect
        }
      }
      correct
    }
    println(s"neighbors $res")
    res.sum
  }

  def toGraph(edges: Array[Array[Int]]): Map[Int, Node] = {
    val n = edges.size + 1
    val seq = (0 until n).map(i => i -> Node(i, mu.Set.empty, mu.Map.empty))
    val graph: Map[Int, Node] = seq.to(Map)

    for (edge <- edges) {
      val (from, to) = (edge(0), edge(1))
      graph(from).neighbors += to
      graph(to).neighbors += from
    }
    graph
  }

  @main
  def test(): Unit = {
    // edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
    println(
      rootCount(
        Array(Array(0, 1), Array(1, 2), Array(1, 3), Array(4, 2)),
        Array(Array(1, 3), Array(0, 1), Array(1, 0), Array(2, 4)),
        3
      )
    ) // 2
  }

}
