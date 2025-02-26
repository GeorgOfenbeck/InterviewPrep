package leetcode.mock23.task2

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def pathSum(root: TreeNode, targetSum: Int): Int = {
    if (root == null) return 0
    if (root.left == null && root.right == null) {
      if (root.value == targetSum) return 1
      else return 0
    }
    val res = recSum(root, targetSum, targetSum, true)
    res

  }

  def recSum(root: TreeNode, targetSum: Int, orig: Int, start: Boolean): Int = {
    val lo = if (root.left == null) None else Some(root.left)
    val ro = if (root.right == null) None else Some(root.right)
    val lwR =
      lo.map(x => recSum(x, targetSum - root.value, orig, false)).getOrElse(0)
    val lwoR = lo.map(x => recSum(x, orig, orig, true)).getOrElse(0)
    val rwR =
      ro.map(x => recSum(x, targetSum - root.value, orig, false)).getOrElse(0)
    val rwOR = ro.map(x => recSum(x, orig, orig, true)).getOrElse(0)

    val isMatch = if (targetSum == root.value) 1 else 0
    val res = Vector(lwoR, lwR, rwOR, rwR, isMatch)
    res.sum
  }

  @main
  def test(): Unit = {
    // [1,null,2,null,3,null,4,null,5]
    println(
      pathSum(
        new TreeNode(
          1,
          null,
          new TreeNode(
            2,
            null,
            new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5)))
          )
        ),
        3
      )
    ) // 2
  }
}
