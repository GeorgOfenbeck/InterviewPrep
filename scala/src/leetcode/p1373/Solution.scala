package leetcode.p1373

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

case class BSTInfo(isEmpty: Boolean, sum: Int, min: Int, max: Int)

object Solution {

  var maxSum = Int.MinValue
  def maxSumBST(root: TreeNode): Int = {
    maxSum = Int.MinValue
    isBST(root)
    if (maxSum < 0) 0 else maxSum
  }

  def isBST(node: TreeNode): Option[BSTInfo] = {
    val lefto: Option[TreeNode] =
      if (node.left == null) None else Some(node.left)
    val righto: Option[TreeNode] =
      if (node.right == null) None else Some(node.right)

    val leftBSTo: Option[BSTInfo] = lefto match {
      case None        => Some(BSTInfo(true, 0, node.value, node.value))
      case Some(value) => isBST(value)
    }
    val rightBSTo: Option[BSTInfo] = righto match {
      case None        => Some(BSTInfo(true, 0, node.value, node.value))
      case Some(value) => isBST(value)
    }

    (leftBSTo, rightBSTo) match {
      case (Some(leftBST), Some(rightBST)) => {
        if (
          (leftBST.isEmpty || leftBST.max < node.value) && (rightBST.isEmpty || rightBST.min > node.value)
        ) {
          val sum = leftBST.sum + rightBST.sum + node.value
          val min = if (leftBST.isEmpty) node.value else leftBST.min
          val max = if (rightBST.isEmpty) node.value else rightBST.max
          if (sum > maxSum) maxSum = sum
          Some(BSTInfo(false, sum, min, max))
        } else {
          None
        }
      }
      case _ => None
    }
  }
}
