package leetcode.mock34.task2

import scala.collection.{mutable => mu}

object Solution {
  def largestValues(root: TreeNode): List[Int] = {
    if (root == null) return List.empty
    dfs(root)
  }

  def dfs(root: TreeNode): List[Int] = {

    val queue = mu.Queue.empty[(TreeNode, Int)]

    queue.enqueue((root, 0))

    var level = 0
    var l = List.empty[Int]
    var curMax = Int.MinValue
    while (queue.nonEmpty) {
      val cur = queue.dequeue()
      val (curNode, curLevel) = cur

      if (curLevel == level) {
        curMax = Math.max(curMax, curNode.value)
        if (curNode.left != null) queue.enqueue((curNode.left, curLevel + 1))
        if (curNode.right != null) queue.enqueue((curNode.right, curLevel + 1))
      } else {
        l = curMax +: l
        curMax = curNode.value
        level = curLevel
      }
    }
    l = curMax +: l
    l
  }
}
