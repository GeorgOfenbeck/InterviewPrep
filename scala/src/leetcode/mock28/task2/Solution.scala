package leetcode.mock28.task2

import scala.collection.{mutable => mu}

object Solution {
  def closestValue(root: TreeNode, target: Double): Int = {
    if (root.left == null && root.right == null)
      root.value
    else
      findBorders(root, target, None, None)
  }

  def findBorders(
      root: TreeNode,
      target: Double,
      llt: Option[Int],
      lgt: Option[Int]
  ): Int = {
    if (root.value > target) {
      if (root.left == null) {
        getClosest(llt, Some(root.value), target)
      } else
        findBorders(root.left, target, llt, Some(root.value))
    } else {
      if (root.right == null) {
        getClosest(Some(root.value), lgt, target)
      } else
        findBorders(root.right, target, Some(root.value), lgt)
    }
  }

  def getClosest(a: Option[Int], b: Option[Int], target: Double): Int = {
    (a, b) match {
      case (Some(x), Some(y)) => {
        val gt = y - target
        val lt = target - x
        if (gt < lt) y else x
      }
      case (Some(x), None) => x
      case (None, Some(y)) => y
      case (None, None)    => assert(false)
    }
  }
}
