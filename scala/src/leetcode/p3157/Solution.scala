package leetcode.p3157

import scala.collection.{mutable => mu}

object Solution {
  def minimumLevel(root: TreeNode): Int = {
    bfs(root)
  }

  def bfs(root: TreeNode): Int = {

    var minsum: Long = Long.MaxValue

    // we need a queue for the next level

    val queue = mu.Queue.empty[(TreeNode, Int)]

    queue.enqueue((root, 1))

    var sum: Long = 0
    var activeLevel = 1
    var minLevel = 1

    while (queue.nonEmpty) {
      val (cur, curLevel) = queue.dequeue()
      if (activeLevel != curLevel) {
        if (sum < minsum) {
          minsum = sum
          minLevel = activeLevel
        }
        sum = 0
      }
      activeLevel = curLevel
      sum = sum + cur.value
      if (cur.left != null) queue.enqueue((cur.left, curLevel + 1))
      if (cur.right != null) queue.enqueue((cur.right, curLevel + 1))
    }
    if (sum < minsum) {
      minLevel = activeLevel
    }

    minLevel
  }

  @main
  def test(): Unit = {
    // [7,8,6,6]
    println(
      minimumLevel(
        new TreeNode(
          7,
          new TreeNode(8, new TreeNode(6), null),
          new TreeNode(6, null, null)
        )
      ) == 3
    )
  }
}
