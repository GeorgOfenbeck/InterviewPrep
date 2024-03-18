package leetcode.p207

import scala.annotation.tailrec

/*

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
object Solution extends App {

  //val n = 2
  //  val courses = Array(Array(0,1))
  val n = 3
  val courses = Array(Array(1, 0), Array(1, 2), Array(0, 1))
  //Array(Array(0,1),Array(1,0))

  println(canFinish(n, courses))

  case class Node(id: Int, in: Set[Int], out: Set[Int]) {
    override def toString: String = {
      s"${id} in: ${in} out: ${out}"
    }
  }

  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    if (numCourses <= 1 || prerequisites.isEmpty) return true


    val (nodes,nonroots): (Map[Int, Node],Set[Int]) = prerequisites.foldLeft( (Map.empty[Int, Node], Set.empty[Int]))(
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

    //nodes.map(println(_))

    //val roots = nodes.filter{ case (k,v) => v.in.isEmpty}.map(x => x._1)

    //roots.map(println(_))
    //val roots = forward.map { case (id, next) => if (next.size == 1) Set(id) else Set.empty[Int] }.reduce((a, b) => a ++ b)

    /*
    val rootsv = nodes.map { case (id, node) => if (node.in.isEmpty) Set(id) else Set.empty[Int] }
    val roots = rootsv.reduce((a, b) => a ++ b)

    if (roots.isEmpty) false
    else
      traverse(roots, nodes, Map.empty)*/



    purge(nodes,roots)
  }




  @tailrec
  def purge(edges: Map[Int, Node], roots: Set[Int]): Boolean = {
    if (edges.isEmpty) true
    else {
      if (roots.isEmpty) false
      else
        {
          val (purged,nroots) = purgeRoots(roots,edges)
          purge(purged,nroots)
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


  def traverse(roots: Set[Int], edges: Map[Int, Node], visited: Map[Int, Int]): Boolean = {
    var duplicate = false
    val next = roots.map(root => edges(root).out).toVector
    if (next.isEmpty) return true;
    val nextset = next.reduce((a, b) => a ++ b)
    val nvisited = next.foldLeft(visited)((acc, ele) => { //for all neighbour sets
      ele.foldLeft(acc)(
        (acc2, ele2) => { //for one neigh
          val nacc = acc2 + (ele2 -> (acc2.getOrElse(ele2, 0) + 1))
          if (nacc(ele2) > edges(ele2).in.size)
            duplicate = true
          nacc
        }
      )
    })
    if (duplicate) false
    else {
      val nnext = nextset -- visited.keySet
      if (nnext.isEmpty) return true
      else traverse(nnext, edges, nvisited)
    }
  }
}