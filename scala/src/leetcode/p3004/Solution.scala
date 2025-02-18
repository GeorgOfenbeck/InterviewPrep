package leetcode.p3004

import scala.collection.{mutable => mu}

case class Tree(
    id: Int,
    var children: mu.Set[Tree],
    var color: Int,
    var sameColor: Boolean,
    var biggestSubTree: Int
) {}

object Solution {
  def maximumSubtreeSize(edges: Array[Array[Int]], colors: Array[Int]): Int = {
    val tree = edgesToTree(edges, colors)
    updateSubtreeSize(tree)
  }

  def updateSubtreeSize(tree: Tree): Int = {
    if (tree.children.isEmpty) {
      tree.biggestSubTree = 1
      tree.sameColor = true
      return 1
    }
    tree.children.map(updateSubtreeSize)

    val colorset = tree.children.map(c => c.color)
    val sameColorSet = tree.children.map(c => c.sameColor)

    if (
      sameColorSet.size == 1 && sameColorSet.contains(
        true
      ) && colorset.size == 1 && colorset.contains(tree.color)
    ) {
      tree.sameColor = true
      tree.biggestSubTree = tree.children.toVector.map(_.biggestSubTree).sum + 1
      return tree.biggestSubTree
    } else {
      tree.sameColor = false
      tree.biggestSubTree = tree.children.toVector.map(_.biggestSubTree).max
      return tree.biggestSubTree
    }
  }

  // we assume that the edges are given in the form of a tree
  def edgesToTree(edges: Array[Array[Int]], colors: Array[Int]): Tree = {

    val tree = new Tree(0, mu.Set.empty, colors(0), false, 0)

    val tmap = mu.Map.empty[Int, Tree]
    tmap.addOne((0 -> tree))
    for (edge <- edges) {
      val parentid = edge(0)
      val childid = edge(1)

      val parent =
        tmap.getOrElseUpdate(
          parentid,
          Tree(parentid, mu.Set.empty, colors(parentid), false, 0)
        )
      val child =
        tmap.getOrElseUpdate(
          childid,
          Tree(childid, mu.Set.empty, colors(childid), false, 0)
        )
      parent.children.add(child)
    }
    tmap(0)

  }

  @main
  def test(): Unit = {
    {
      // edges = [[0,1],[0,2],[2,3],[2,4]], colors = [1,2,3,3,3]
      val edges = Array(Array(0, 1), Array(0, 2), Array(2, 3), Array(2, 4))
      val colors = Array(1, 2, 3, 3, 3)
      println(maximumSubtreeSize(edges, colors))
    }
    {
      val edges = Array(Array(0, 1), Array(1, 2))
      val colors = Array(1, 1, 2)
      println(maximumSubtreeSize(edges, colors))
    }
  }
}
