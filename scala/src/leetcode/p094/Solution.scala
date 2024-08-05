package leetcode.p094

import scala.annotation.tailrec

class TreeNode(
    _value: Int = 0,
    _left: TreeNode = null,
    _right: TreeNode = null
) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object Solution {
  def inorderTraversal(root: TreeNode): List[Int] = {
    inorderTraversal(root, List.empty[Int])//.reverse
  }

  def inorderTraversal(root: TreeNode, sofar: List[Int]): List[Int] = {
    if (root == null)
      return sofar
    else {
      val left = inorderTraversal(root.right, sofar)
      val center = root.value +: left
      val right = inorderTraversal(root.left, center)
      right
    }
  }
}
