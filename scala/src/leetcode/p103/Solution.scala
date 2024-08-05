package leetcode.p103

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
  def zigzagLevelOrder(root: TreeNode): List[List[Int]] = {

    if (root == null)
      return List.empty
    var res = List.empty[List[Int]]
    var fromLeft = true

    var parents = List(root)

    while (parents.nonEmpty) {
      val (children, level) = oneLevel(parents, fromLeft)
      fromLeft = !fromLeft
      parents = children
      res = level +: res
    }

    res.reverse
  }

  def oneLevel(
      parents: List[TreeNode],
      fromLeft: Boolean
  ): (List[TreeNode], List[Int]) = {
    var level = List.empty[Int]
    var children = List.empty[TreeNode]
    for (node <- parents) {
      level = node.value +: level
      if (node.right != null)
        children = node.right +: children
      if (node.left != null)
        children = node.left +: children
    }
    if (fromLeft)
      (children, level)
    else
      (children.reverse, level.reverse)
  }
}
