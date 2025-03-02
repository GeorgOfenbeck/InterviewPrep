package leetcode.p207

import scala.collection.{mutable => mu}

case class Node(id: Int, to: Set[Int], from: Set[Int])

object Solution {
  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    // n courses 0 - n
    // edge -> arr(0) <- arr(b)
    //

    // get all leaves (no perquisites)
    // remove them from graph -> remove them as prerequisites
    // repeat until no leaves left => true - otherwise false

    val graph = toGraph(numCourses, prerequisites)

    var leaves = graph.values.filter(_.from.isEmpty).map(_.id).toSet
    var remainGraph = graph

    while (leaves.nonEmpty) {
      println(s"$leaves")
      // println(s" $remainGraph")
      val res = removeNodes(leaves, remainGraph)
      remainGraph = res._2
      leaves = res._1
    }
    if (remainGraph.size > 0) return false
    else true
  }

  def removeNodes(
      leaves: Set[Int],
      graph: Map[Int, Node]
  ): (Set[Int], Map[Int, Node]) = {
    leaves.foldLeft((Set.empty[Int], graph: Map[Int, Node])) { (acc, ele) =>
      {
        val (_, ngraph) = acc
        var nleaves = acc._1

        // get leafNode
        val leafNode = graph(ele)

        // for each leafnode - remove it from the succ from
        val updatedGraph = leafNode.to.foldLeft(ngraph) { (acc, to) =>
          val toNode = acc(to)
          val without = toNode.copy(from = toNode.from - ele)
          if (without.from.isEmpty) {
            nleaves = nleaves + to
          }
          acc + (to -> without)
        }
        // remove yourself from the graph
        (nleaves, updatedGraph - ele)
      }
    }
  }

  def toGraph(
      numCourses: Int,
      prerequisites: Array[Array[Int]]
  ): Map[Int, Node] = {

    val emtyGraph =
      (0 until numCourses).map(i => i -> Node(i, Set.empty, Set.empty)).toMap

    prerequisites.foldLeft(emtyGraph) { case (graph, arr) =>
      val (a, b) = (arr(0), arr(1))
      if (a == b) {
        val nodeA = graph(a)
        graph + (a -> nodeA.copy(to = nodeA.to + b, from = nodeA.from + b))
      } else {
        val nodeA = graph(a)
        val nodeB = graph(b)
        val newA = nodeA.copy(from = nodeA.from + b)
        val newB = nodeB.copy(to = nodeB.to + a)
        graph + (a -> newA) + (b -> newB)
      }
    }
  }

  @main
  def test(): Unit = {
    // [[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]], 20
    println(
      canFinish(
        20,
        Array(
          Array(0, 10),
          Array(3, 18),
          Array(5, 5),
          Array(6, 11),
          Array(11, 14),
          Array(13, 1),
          Array(15, 1),
          Array(17, 4)
        )
      )
    )

  }
}
