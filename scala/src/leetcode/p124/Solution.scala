package leetcode.p124

import scala.collection.{mutable => mu}

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */
object Solution {
  def maxPathSum(root: TreeNode): Int = {
    val res = maxPossible(Some(root)).get
    Math.max(res.toTop, res.noTop)
  }

  case class Res(toTop: Int, noTop: Int)

  def maxPossible(rooto: Option[TreeNode]): Option[Res] = {

    rooto match {
      case None => None
      case Some(root) => {
        val leftot: Option[TreeNode] =
          if (root.left == null) None else Some(root.left)
        val rightot: Option[TreeNode] =
          if (root.right == null) None else Some(root.right)

        val lefto = maxPossible(leftot)
        val righto = maxPossible(rightot)

        (lefto, righto) match {
          case (Some(left), Some(right)) => {
            val toTop = Vector(
              Math.max(left.toTop, right.toTop) + root.value,
              root.value
            ).max
            val noTop = Vector(
              left.toTop + right.toTop + root.value,
              left.toTop,
              right.toTop,
              left.noTop,
              right.noTop
            ).max
            Some(Res(toTop, noTop))
          }
          case (None, None) => {
            Some(Res(root.value, root.value))
          }

          case (Some(left), None) => {
            val toTop = Vector(left.toTop + root.value, root.value).max
            val noTop = Vector(
              left.toTop + root.value,
              left.toTop,
              left.noTop
            ).max
            Some(Res(toTop, noTop))
          }

          case (None, Some(left)) => {
            val toTop = Vector(left.toTop + root.value, root.value).max
            val noTop = Vector(
              left.toTop + root.value,
              left.toTop,
              left.noTop
            ).max
            Some(Res(toTop, noTop))
          }

        }
        //
        // val toTop = Math.max(left.toTop, right.toTop) + root.value
        // val noTop = Vector(
        //   left.toTop + right.toTop + root.value,
        //   left.toTop,
        //   right.toTop,
        //   left.noTop,
        //   right.noTop
        // ).max
        // Res(toTop, noTop)
      }
    }
  }
  @main
  def test(): Unit = {
    test3()
  }

  def test3(): Unit = {
    println(
      maxPathSum(
        new TreeNode(
          -2,
          new TreeNode(-1),
          new TreeNode(-10)
        )
      )
    )
  }

  def test2(): Unit = {
    // root = [-10,9,20,null,null,15,7]
    println(
      maxPathSum(
        new TreeNode(
          -10,
          new TreeNode(9),
          new TreeNode(20, new TreeNode(15), new TreeNode(7))
        )
      )
    )
  }

}
