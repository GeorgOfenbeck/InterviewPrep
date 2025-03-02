package leetcode.p113

object Solution {
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    rec(root, targetSum, List.empty, List.empty)
  }

  def rec(
      root: TreeNode,
      targetSum: Int,
      sofar: List[Int],
      combine: List[List[Int]]
  ): List[List[Int]] = {

    if (root == null) return combine

    // we are a leaf
    if (root.left == null && root.right == null) {
      if (targetSum == root.value) {
        return (root.value +: sofar).reverse +: combine
      } else {
        return combine
      }
    }

    val ncombine = if (root.left != null) {
      rec(root.left, targetSum - root.value, root.value +: sofar, combine)
    } else combine

    if (root.right != null) {
      rec(root.right, targetSum - root.value, root.value +: sofar, ncombine)
    } else ncombine
  }
}
