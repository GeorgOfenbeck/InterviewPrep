package leetcode.p337

import scala.collection.{mutable => mu}

object Solution {
  def rob(root: TreeNode): Int = {
    if (root == null) return 0
    getOpt(Some(root), mu.Map.empty[TreeNode, Int])
  }

  def getOpt(rootOption: Option[TreeNode], cost: mu.Map[TreeNode, Int]): Int = {
    return rootOption match {
      case Some(root) => {
        if (cost.contains(root)) {
          return cost(root)
        } else {
          val ll: Int =
            getOpt(getLeft(getLeft(rootOption)), cost)
          val lr: Int =
            getOpt(getRight(getLeft(rootOption)), cost)

          val rl: Int =
            getOpt(getLeft(getRight(rootOption)), cost)

          val rr: Int =
            getOpt(getRight(getRight(rootOption)), cost)

          val l: Int = getOpt(getLeft(rootOption), cost)

          val r: Int = getOpt(getRight(rootOption), cost)

          val withroot: Int = root.value + ll + lr + rl + rr
          val withoutroot: Int = l + r

          val opt = Math.max(withroot, withoutroot)
          cost.addOne((root -> opt))
          opt

        }
      }
      case None => 0
    }

  }

  def getLeft(root: Option[TreeNode]): Option[TreeNode] = {
    root match {
      case Some(node) => {
        if (node.left != null) Some(node.left)
        else None
      }
      case None => None
    }
  }
  def getRight(root: Option[TreeNode]): Option[TreeNode] = {
    root match {
      case Some(node) => {
        if (node.right != null) Some(node.right)
        else None
      }
      case None => None
    }
  }
}
