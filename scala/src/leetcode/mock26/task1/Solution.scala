/*

bject Solution {
  def trimBST(root: TreeNode, low: Int, high: Int): TreeNode = {

    rec(root, low, high)
  }

  def rec(root: TreeNode, low: Int, high: Int): TreeNode = {
    if (root == null) return null
    if (root.value < low)
      return rec(root.left, low, high)
    if (root.value > high)
      return rec(root.right, low, high)
    if (root.left != null) {
      val left = root.left

      // drop smaller on left
      if (left.value == low) {
        root.left.left = null
      }

      if (left.value < low) {
        root.left = root.left.right
        root.left = rec(root.left, low, high)
      }
      if (left.value > low) {
        root.left = rec(root.left, low, high)
      }
    }
    if (root.right != null) {
      val right = root.right

      // drop bigger on right
      if (right.value == high) {
        root.right.right = null
      }
      // drop right side as its even bigger - move left up
      if (right.value > high) {
        root.right = root.right.left
        root.right = rec(root.right, low, high)
      }
      //
      if (right.value < high) {
        root.right = rec(root.right, low, high)
      }
    }
    root
  }
}
 */
