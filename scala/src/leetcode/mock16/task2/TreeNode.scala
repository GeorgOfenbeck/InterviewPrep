package leetcode.mock16.task2

import scala.collection.{mutable => mu}
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
