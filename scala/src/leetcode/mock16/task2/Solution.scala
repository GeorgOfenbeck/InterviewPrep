package leetcode.mock16.task2

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

/** Definition for a binary tree node. class TreeNode(_value: Int = 0, _left:
  * TreeNode = null, _right: TreeNode = null) { var value: Int = _value var
  * left: TreeNode = _left var right: TreeNode = _right }
  */

object Solution {
  def deepestLeavesSum(root: TreeNode): Int = {
    if (root == null) return 0
    bfs(root)
  }

  def bfs(root: TreeNode): Int = {
    var levelsum = 0
    var level = 0
    val queue: mu.Queue[(TreeNode, Int)] = mu.Queue.empty[(TreeNode, Int)]
    queue.enqueue((root, 0))

    while (queue.nonEmpty) {
      val (cur, curlevel) = queue.dequeue()
      if (level == curlevel) { // we are still on the same level
        levelsum += cur.value
      } else {
        levelsum = 0
        levelsum += cur.value
        level = curlevel
      }
      if (cur.left != null) queue.enqueue((cur.left, curlevel + 1))
      if (cur.right != null) queue.enqueue((cur.right, curlevel + 1))
    }
    levelsum
  }
}
