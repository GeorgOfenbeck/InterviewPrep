package leetcode.p968

import scala.collection.{mutable => mu}

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */
object Solution {
  def minCameraCover(root: TreeNode): Int = {
    /// debugTree(root, Vector.empty)
    if (root.left == null && root.right == null) return 1
    val cache = mu.Map.empty[(Vector[Boolean], Boolean, Boolean), Option[Int]]
    val leftw = rec(root.left, true, false, Vector(true), cache)
    val rightw = rec(root.right, true, false, Vector(false), cache)

    val leftwo = rec(root.left, false, false, Vector(true), cache)
    val rightwo = rec(root.right, false, false, Vector(false), cache)

    (leftw, rightw, leftwo, rightwo) match {
      case (Some(lw), Some(rw), Some(lwo), Some(rwo)) =>
        Vector((lw + rw + 1), (lwo + rwo)).min
      case (Some(lw), Some(rw), Some(l), None) => Vector((lw + rw + 1), (l)).min
      case (Some(lw), Some(rw), None, Some(r)) => Vector((lw + rw + 1), (r)).min
      case _                                   => -1
    }
  }

  def rec(
      root: TreeNode,
      parent: Boolean,
      gparent: Boolean,
      path: Vector[Boolean],
      cache: mu.Map[(Vector[Boolean], Boolean, Boolean), Option[Int]]
  ): Option[Int] = {
    if (cache.contains((path, parent, gparent))) {
      return cache((path, parent, gparent))
    }
    val res: Option[Int] = (gparent, parent) match {
      case (false, false) => { // we need camera
        if (root == null) None
        else {
          val left = rec(root.left, true, parent, true +: path, cache)
          val right = rec(root.right, true, parent, false +: path, cache)
          (left, right) match {
            case (Some(l), Some(r)) => Some(l + r + 1)
            case (Some(l), None)    => Some(l + 1)
            case (None, Some(r))    => Some(r + 1)
            case _                  => None
          }
        }
      }
      case _ => {
// we dont need camera - but our child does
        if (root == null) Some(0)
        else {
          val uswith = {
            val left = rec(root.left, true, parent, true +: path, cache)
            val right = rec(root.right, true, parent, false +: path, cache)
            (left, right) match {
              case (Some(l), Some(r)) => Some(l + r + 1)
              case (Some(l), None)    => Some(l + 1)
              case (None, Some(r))    => Some(r + 1)
              case _                  => None
            }
          }
          val uswithout = {
            val left = rec(root.left, false, parent, true +: path, cache)
            val right = rec(root.right, false, parent, false +: path, cache)
            (left, right) match {
              case (Some(l), Some(r)) => Some(l + r)
              case (Some(l), None)    => Some(l)
              case (None, Some(r))    => Some(r)
              case _                  => None

            }
          }
          (uswith, uswithout) match {
            case (Some(l), Some(r)) => Some(l min r)
            case (Some(l), None)    => Some(l)
            case (None, Some(r))    => Some(r)
            case _                  => None
          }
        }
      }
    }
    cache((path, parent, gparent)) = res
    // if (root != null)
    //   println(s"${root.value} $parent $gparent $res")
    res
  }

  def debugTree(root: TreeNode, path: Vector[Boolean]): Unit = {
    if (root == null) return
    println(s"${root.value} $path")
    debugTree(root.left, true +: path)
    debugTree(root.right, false +: path)
  }

  @main
  def test(): Unit = {
    println(
      minCameraCover(
        new TreeNode(
          1,
          new TreeNode(
            2,
            null,
            new TreeNode(3, new TreeNode(4, null, TreeNode(5, TreeNode(6))))
          )
        )
      )
    )
  }
  @main
  def test4(): Unit = {
    println(
      minCameraCover(
        new TreeNode(
          1,
          new TreeNode(
            2,
            new TreeNode(3, new TreeNode(4, TreeNode(5, TreeNode(6))))
          )
        )
      )
    )
  }

  @main
  def test3(): Unit = {
    println(
      minCameraCover(
        new TreeNode(
          1,
          new TreeNode(
            2,
            null,
            new TreeNode(3)
          )
        )
      )
    )
  }

  @main
  def test2(): Unit = {
    // [0,0,null,0,null,0,null,null,0]
    println(
      minCameraCover(
        new TreeNode(
          1,
          new TreeNode(
            2,
            new TreeNode(
              3,
              new TreeNode(4, null, new TreeNode(5))
            )
          )
        )
      )
    )
  }
}
