package leetcode.p685

import scala.annotation.tailrec


object Solution extends App{
  
  val a =
  //Array(Array(1,2),Array(2,3),Array(3,4),Array(4,1),Array(1,5))

    //Array(Array(2,1),Array(3,1),Array(4,2),Array(1,4))
      Array(Array(4,2),Array(1,5),Array(5,2),Array(5,3),Array(2,4))
  findRedundantDirectedConnection(a).map(println(_))

  case class Node(id: Int, in: Set[Int], out: Set[Int], when: Int)

  def findRedundantDirectedConnection(edges: Array[Array[Int]]): Array[Int] = {
    breakFoldLeft(Map.empty,edges,0, Array.empty)
    /*var offender: Array[Int] = Array.empty
    val graph = edges.foldLeft(Map.empty[Int, Node])( (acc,ele) => {
      val u = ele (0)
      val v = ele (1)

      val parent = acc.getOrElse(u, Node(u,Set.empty,Set.empty))
      val child = acc.getOrElse(v, Node(u,Set.empty,Set.empty))

      val nParent = parent.copy(out = parent.out + v)
      val nChild = child.copy(in = child.in + u)

      if (child.in.size > 1) //thats the offender
        {
          offender = Array(u,v)
          break
        }
      else
        acc + (u -> nParent) + (v -> nChild)
    })
    offender */

  }

  @tailrec
  def breakFoldLeft(acc: Map[Int, Node], edges: Array[Array[Int]], idx: Int, offender: Array[Int]): Array[Int] = {
    if (idx == edges.size) {
      //we did not find an offender - there must be a cycle
      val roots_in = acc.filter{case (k,v) => v.in.isEmpty }.map(x => x._1).toSet
      val afterPurge_forward = purge(acc,roots_in)

      val roots_out = afterPurge_forward.filter{case (k,v) => v.out.isEmpty }.map(x => x._1).toSet
      val afterPurge = purge(afterPurge_forward,roots_out)

      if (afterPurge.isEmpty) return offender
      else {
        val twoParents = afterPurge.filter{ case (k,v) => acc(k).in.size > 1}
        if (!twoParents.isEmpty) {
          val (k,v) = twoParents.head
          val xx = v.in.filter( p => afterPurge.contains(p))
          Array(xx.head,k)
        } else
        edges.foldLeft( Array.empty[Int])(
          (acc,ele) => {
            val u = ele(0)
            val v = ele(1)
            if (afterPurge.contains(u) && afterPurge.contains(v))
              Array(u,v)
            else
              acc
          }
        )
      }
    } else {
      val ele = edges(idx)
      val u = ele(0)
      val v = ele(1)
      val parent = acc.getOrElse(u, Node(u, Set.empty, Set.empty, idx))
      val child = acc.getOrElse(v, Node(v, Set.empty, Set.empty, idx))
      val nParent = parent.copy(out = parent.out + v)
      val nChild = child.copy(in = child.in + u)
      if (nChild.in.size > 1) //thats the offender
        breakFoldLeft(acc + (u -> nParent) + (v -> nChild), edges, idx + 1, Array(u, v))
      else
        breakFoldLeft(acc + (u -> nParent) + (v -> nChild), edges, idx + 1,offender)
    }
  }


  @tailrec
  def purge_back(edges: Map[Int, Node], roots: Set[Int]): Map[Int,Node] = {
    if (edges.isEmpty) edges
    else {
      if (roots.isEmpty) edges
      else
      {
        val (purged,nroots) = purgeRoots_back(roots,edges)
        purge_back(purged,nroots)
      }
    }
  }


  def purgeRoot_back(root: Node, edges: Map[Int, Node], nextroots: Set[Int]): (Map[Int, Node], Set[Int]) = {
    root.in.foldLeft( (edges,nextroots))(
      (acc2,ele) => {
        val (acc,set) = acc2
        val sofar = acc(ele) //what we point to
        val newin = sofar.out - root.id
        val nacc = acc + (ele -> sofar.copy(out = newin))
        if (newin.isEmpty)
          (nacc,set + ele)
        else
          (nacc,set)
      }
    )
  }

  def purgeRoots_back(roots: Set[Int], edges: Map[Int, Node]):(Map[Int, Node], Set[Int]) = {
    val rootnodes = roots.map(edges(_))
    val withoutroots = edges.filter{case (k,v) => !roots.contains(k)}
    rootnodes.foldLeft( (withoutroots, Set.empty[Int]))(
      (acc,ele) => purgeRoot_back(ele,acc._1,acc._2)
    )
  }



  @tailrec
  def purge(edges: Map[Int, Node], roots: Set[Int]): Map[Int,Node] = {
    if (edges.isEmpty) edges
    else {
      if (roots.isEmpty) edges
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
        val sofar = acc(ele) //what we point to
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


/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */