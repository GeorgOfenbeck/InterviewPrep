package leetcode.p210

import scala.annotation.tailrec

/*
210. Course Schedule II
Medium

1385

96

Favorite

Share
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

 */
object Solution extends App{

  val n = 2
  val courses = Array(Array(1, 0)) //, Array(1, 2), Array(0, 1))



  findOrder(n,courses).map(println(_))

  def findOrder(numCourses: Int, prerequisites: Array[Array[Int]]): Array[Int] = {
    if (numCourses == 0) return Array.empty[Int]

    val nopre: Map[Int, Node] = (0 until numCourses).map( e => (e -> Node(e, Set.empty, Set.empty))).toMap

    val (nodes,nonroots): (Map[Int, Node],Set[Int]) = prerequisites.foldLeft( (nopre, Set.empty[Int]))(
      (acc2, ele) => {
        val (acc,set) = acc2
        val from = ele(1)
        val to = ele(0)
        val fromNode = acc.getOrElse(from, Node(from, Set.empty, Set.empty))
        val toNode = acc.getOrElse(to, Node(to, Set.empty, Set.empty))
        val nFromNode = fromNode.copy(out = (fromNode.out + to))
        val nToNode = toNode.copy(in = (toNode.in + from))
        val nacc = acc + (from -> nFromNode) + (to -> nToNode)
        (nacc,set + to)
      })

    val roots = nodes.keySet -- nonroots

    val res = purge(nodes,roots,roots.toList).toArray
    if (res.size == numCourses) res else Array.empty

  }
  case class Node(id: Int, in: Set[Int], out: Set[Int]) {
    override def toString: String = {
      s"${id} in: ${in} out: ${out}"
    }
  }



  @tailrec
  private def purge(edges: Map[Int, Node], roots: Set[Int], order: List[Int]): List[Int] = {
    if (edges.isEmpty) order
    else {
      if (roots.isEmpty) order
      else
      {
        val (purged,nroots) = purgeRoots(roots,edges)
        val norder = order ++ nroots.toList
        purge(purged,nroots, norder)
      }
    }
  }


  def purgeRoot(root: Node, edges: Map[Int, Node], nextroots: Set[Int]): (Map[Int, Node], Set[Int]) = {
    root.out.foldLeft( (edges,nextroots))(
      (acc2,ele) => {
        val (acc,set) = acc2
        val sofar = acc(ele)
        val newin = sofar.in - root.id
        val nacc = acc + (ele -> sofar.copy(in = newin))
        if (newin.isEmpty)
          (nacc,set + ele)
        else
          (nacc,set)
      }
    )
  }

  def purgeRoots(roots: Set[Int], edges: Map[Int, Node]):(Map[Int, Node], Set[Int]) = {
    val rootnodes = roots.map(edges(_))
    val withoutroots = edges.filter{case (k,v) => !roots.contains(k)}
    rootnodes.foldLeft( (withoutroots, Set.empty[Int]))(
      (acc,ele) => purgeRoot(ele,acc._1,acc._2)
    )
  }
}
