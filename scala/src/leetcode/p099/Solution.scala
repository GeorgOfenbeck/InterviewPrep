package leetcode.p099

import scala.collection.{mutable => mu}

object Solution {
  def recoverTree(root: TreeNode): Unit = {
    val left = findIncorrect(root, leftFirst = true, None, None).get
    val right = findIncorrect(root, leftFirst = false, None, None).get
    val tmp = left.value
    left.value = right.value
    right.value = tmp
  }

  def findIncorrect(
      root: TreeNode,
      leftFirst: Boolean,
      min: Option[Int],
      max: Option[Int]
  ): Option[TreeNode] = {

    val left: Option[TreeNode] =
      if (root.left != null) Some(root.left) else None
    val right: Option[TreeNode] =
      if (root.right != null) Some(root.right) else None

    val pre = if (leftFirst) left else right
    val post = if (leftFirst) right else left

    // find the first incorrect node in the right

    val preRes: Option[TreeNode] =
      if (leftFirst)
        pre.map(findIncorrect(_, leftFirst, min, Some(root.value))).flatten
      else
        pre.map(findIncorrect(_, leftFirst, Some(root.value), max)).flatten

    preRes match {
      case Some(value) => return Some(value)
      case None => {
        // the pre is correct -> check the root
        if (
          min
            .map(m => m > root.value)
            .getOrElse(false) || max.map(m => m < root.value).getOrElse(false)
        ) {
          return Some(root)
        }
        // check in the post
        if (leftFirst)
          post.map(findIncorrect(_, leftFirst, Some(root.value), max)).flatten
        else
          post.map(findIncorrect(_, leftFirst, min, Some(root.value))).flatten
      }
    }
  }
}
