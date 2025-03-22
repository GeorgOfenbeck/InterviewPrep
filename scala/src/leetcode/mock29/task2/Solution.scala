package leetcode.mock29.task2

object Solution {
  def rangeSumBST(root: TreeNode, low: Int, high: Int): Int = {
    if (root == null) return 0
    rangeSumBSTSafe(root, low, high)
  }

  def rangeSumBSTSafe(root: TreeNode, low: Int, high: Int): Int = {
    val lefto: Option[TreeNode] =
      if (root.left == null) None else Some(root.left)
    val righto: Option[TreeNode] =
      if (root.right == null) None else Some(root.right)

    if (root.value > high) {
      lefto.map(left => rangeSumBSTSafe(left, low, high)).getOrElse(0)
    } else {
      if (root.value < low) {
        righto.map(right => rangeSumBSTSafe(right, low, high)).getOrElse(0)
      } else {
        val left = lefto
          .map(left => rangeSumBSTSafe(left, low, high))
          .getOrElse(0)
        val right = righto
          .map(right => rangeSumBSTSafe(right, low, high))
          .getOrElse(0)
        left + right + root.value
      }
    }
  }
}
