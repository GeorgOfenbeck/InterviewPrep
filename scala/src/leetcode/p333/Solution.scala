package leetcode.p333

import scala.collection.{mutable => mu}

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */

case class BSTInfo(isBST: Boolean, min: Int, max: Int, size: Int, maxseen: Int)

object Solution {
  def largestBSTSubtree(root: TreeNode): Int = {
    if (root == null) return 0
    getMinMax(root).maxseen
  }

  def getMinMax(tree: TreeNode): BSTInfo = {

    val left: Option[BSTInfo] =
      if (tree.left != null) Some(getMinMax(tree.left)) else None
    val right: Option[BSTInfo] =
      if (tree.right != null) Some(getMinMax(tree.right)) else None

    val biggestLeft = left.map(x => x.maxseen).getOrElse(0)
    val biggestRight = right.map(x => x.maxseen).getOrElse(0)

    (left, right) match {
      case (Some(left), Some(right)) => {
        if (
          left.isBST && right.isBST && left.max < tree.value && right.min > tree.value
        ) {
          BSTInfo(
            true,
            left.min,
            right.max,
            left.size + right.size + 1,
            biggestRight + biggestLeft + 1
          )
        } else {
          BSTInfo(false, 0, 0, 0, Math.max(biggestRight, biggestLeft))
        }
      }
      case (None, Some(right)) => {
        if (right.isBST && right.min > tree.value) {
          BSTInfo(true, tree.value, right.max, right.size + 1, biggestRight + 1)
        } else {
          BSTInfo(false, 0, 0, 0, biggestRight)
        }
      }
      case (Some(left), None) => {
        if (left.isBST && left.max < tree.value) {
          BSTInfo(true, left.min, tree.value, left.size + 1, biggestLeft + 1)
        } else {
          BSTInfo(false, 0, 0, 0, biggestLeft)
        }
      }
      case (None, None) => BSTInfo(true, tree.value, tree.value, 1, 1)
    }
  }

  @main
  def test(): Unit = {
    // root = [10,5,15,1,8,null,7]
    println(
      largestBSTSubtree(
        new TreeNode(
          10,
          new TreeNode(5, new TreeNode(1), new TreeNode(8)),
          new TreeNode(15, null, new TreeNode(7))
        )
      )
    )
  }

}
