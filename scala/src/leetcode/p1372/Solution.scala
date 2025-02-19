package leetcode.p1372

import scala.collection.{mutable => mu}

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */

case class ZigInfo(
    val leftZig: Int,
    val rightZig: Int,
    val maxsofar: Int
)
object Solution {
  def longestZigZag(root: TreeNode): Int = {
    val info = rec(root)
    Vector(info.leftZig, info.rightZig, info.maxsofar).max
  }

  def rec(node: TreeNode): ZigInfo = {
    val leftinfo: Option[ZigInfo] =
      if (node.left == null) None else Some(rec(node.left))
    val rightinfo: Option[ZigInfo] =
      if (node.right == null) None else Some(rec(node.right))

    (leftinfo, rightinfo) match {
      case (None, None) => ZigInfo(0, 0, 0)
      case (Some(l), None) =>
        ZigInfo(
          l.rightZig + 1,
          0,
          Vector(l.rightZig + 1, l.maxsofar).max
        )
      case (None, Some(r)) =>
        ZigInfo(
          0,
          r.leftZig + 1,
          Vector(r.leftZig + 1, r.maxsofar).max
        )

      case (Some(l), Some(r)) =>
        ZigInfo(
          l.rightZig + 1,
          r.leftZig + 1,
          Vector(l.rightZig + 1, r.leftZig + 1, l.maxsofar, r.maxsofar).max
        )
    }
  }

  @main
  def test(): Unit = {}
  def test1(): Unit = {
    // root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
    println(
      longestZigZag(
        new TreeNode(
          1,
          null,
          new TreeNode(
            1,
            new TreeNode(1, new TreeNode(1), new TreeNode(1)),
            new TreeNode(1, null, new TreeNode(1, null, new TreeNode(1)))
          )
        )
      )
    )
  }
}
