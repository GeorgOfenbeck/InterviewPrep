/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */

package leetcode.p156

object Solution {
  def upsideDownBinaryTree(root: TreeNode): TreeNode = {
    if (root == null) return null
    if (root.left == null) return root
    val res = rec(root.left, root)
    root.right = null
    root.left = null
    res
  }

  def rec(root: TreeNode, parent: TreeNode): TreeNode = {
    if (root.left == null) {
      root.right = parent
      root.left = parent.right
      return root
    } else {
      val newRoot = rec(root.left, root)
      root.right = parent
      root.left = parent.right
      return newRoot
    }
  }
}
