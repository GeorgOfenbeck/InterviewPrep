package leetcode.p2378

import scala.collection.{mutable => mu}

case class Tree(
    val id: Int,
    var value: Int,
    val children: mu.Set[Tree],
    var sumwith: Long,
    var sumwithout: Long
)

object Solution {
  def maxScore(edges: Array[Array[Int]]): Long = {
    val tree = toTree(edges)
    findMaxScore(tree)
    tree2dotGraph(tree, "debug.dot")
    Math.max(tree.sumwithout, tree.sumwith)
  }

  def findMaxScore(tree: Tree): Unit = {
    if (tree.children.isEmpty) {
      tree.sumwith = tree.value
      tree.sumwithout = 0
      return
    }
    tree.children.foreach(findMaxScore)

    // we take parent value - as a consequence we can't take child value - only their subs
    val childmaxWith = tree.children.toVector.map(c => c.sumwithout).sum
    tree.sumwith = Math.max(childmaxWith + tree.value, childmaxWith)

    // we don't take parent value - we can take child value
    val childmaxWithoutV = tree.children.toVector
      .map(child => {
        val rest = tree.children.diff(Set(child))
        val restsum = rest.map(x => x.sumwithout).sum
        restsum + child.sumwith
      })
    val childmaxWithout = childmaxWithoutV :+ childmaxWith

    tree.sumwithout = childmaxWithout.max
  }

  def toTree(edges: Array[Array[Int]]): Tree = {
    val nodes = mu.Map.empty[Int, Tree]

    val root = Tree(0, 0, mu.Set.empty, 0, 0)
    nodes += (0 -> root)
    for (i <- 1 until edges.size) {
      val (parent, value) = (edges(i)(0), edges(i)(1))
      val child = Tree(i, value, mu.Set.empty, 0, 0)
      nodes += (i -> child)
    }

    for (i <- 1 until edges.size) {
      val (parent, value) = (edges(i)(0), edges(i)(1))
      nodes.updateWith(parent)(parop => {
        parop.map(tree => {
          tree.children.add(nodes(i))
          tree
        })
      })
    }
    root

  }

  def printTree(tree: Tree): Unit = {
    println(
      s"Tree(${tree.id}, ${tree.value})"
    )
    tree.children.foreach(printTree)
  }

  def tree2dotGraph(root: Tree, filename: String): Unit = {
    val writer = new java.io.PrintWriter(filename)
    writer.println("digraph G {")

    def traverse(node: Tree): Unit = {
      node.children.foreach { child =>
        writer.println(s"""  "${node.value}" -> "${child.value}";""")
        traverse(child)
      }
    }

    traverse(root)
    writer.println("}")
    writer.close()
  }

  @main
  def test(): Unit = {
    test3()
  }
  def test3(): Unit = {
    // [[-1,-1],[5,551218],[4,866844],[0,828151],[6,17412],[7,-298822],[3,700735],[6,-884559]]
    println(
      maxScore(
        Array(
          Array(-1, -1),
          Array(5, 551218),
          Array(4, 866844),
          Array(0, 828151),
          Array(6, 17412),
          Array(7, -298822),
          Array(3, 700735),
          Array(6, -884559)
        )
      )
    )
  }
  def test2(): Unit = {
    // [[-1,-1],[0,-282097],[1,456267]]
    println(
      maxScore(
        Array(
          Array(-1, -1),
          Array(0, -282097),
          Array(1, 456267)
        )
      )
    )
  }
  def test1(): Unit = {
    // Input: edges = [[-1,-1],[0,5],[0,10],[2,6],[2,4]]
    println(
      maxScore(
        Array(
          Array(-1, -1),
          Array(0, 5),
          Array(0, 10),
          Array(2, 6),
          Array(2, 4)
        )
      )
    )
  }
}
