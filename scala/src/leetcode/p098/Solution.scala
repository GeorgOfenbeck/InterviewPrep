package leetcode.p098

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */
object Solution {
  def isValidBST(root: TreeNode): Boolean = {
    isValidBST(Some(root), None, None)
  }

  def isValidBST(
      rooto: Option[TreeNode],
      min: Option[Int],
      max: Option[Int]
  ): Boolean = {
    rooto match {
      case None => return true
      case Some(root) => {
        if (
          min
            .map(root.value <= _)
            .getOrElse(false) || max.map(root.value >= _).getOrElse(false)
        ) {
          return false
        }
        val lefto: Option[TreeNode] =
          if (root.left != null) Some(root.left) else None
        val righto: Option[TreeNode] =
          if (root.right != null) Some(root.right) else None

        val leftIsBST = lefto
          .map(left =>
            root.value > left.value && isValidBST(
              Some(left),
              min,
              Some(root.value)
            )
          )
          .getOrElse(true)
        val rightIsBST = righto
          .map(right =>
            root.value < right.value && isValidBST(
              Some(right),
              Some(root.value),
              max
            )
          )
          .getOrElse(true)

        leftIsBST && rightIsBST
      }
    }
  }
}
