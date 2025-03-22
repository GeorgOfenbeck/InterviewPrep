package leetcode.p337

import scala.collection.{mutable => mu}

//n = 1 to 10^4
//Node val > 0

//no two adjacent nodes can be robbed

object Solution {
  def rob(root: TreeNode): Int = {
    val (withRoot, withoutRoot) = rec(root)
    Math.max(withRoot, withoutRoot)
  }

  // (with, without)
  def rec(root: TreeNode): (Int, Int) = {
    val lefto = Option(root.left)
    val righto = Option(root.right)

    val (lw, lwo) = lefto.map(rec).getOrElse((0, 0))
    val (rw, rwo) = righto.map(rec).getOrElse((0, 0))

    // include the root
    val withRoot = lwo + rwo + root.value

    // dont include the root
    val withoutRoot = Math.max(lw, lwo) + Math.max(rw, rwo)
    println(s"${root.value} -> $withRoot, $withoutRoot")
    (withRoot, withoutRoot)
  }

  @main
  def test(): Unit = {
    val tree = TreeNode(
      3,
      TreeNode(4, TreeNode(1), TreeNode(3)),
      TreeNode(5, null, TreeNode(1))
    )
    println(rob(tree))

  }
}
