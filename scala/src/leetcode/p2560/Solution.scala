package leetcode.p2560

import scala.collection.{mutable => mu}

object Solution {
  var min = Int.MaxValue

  def minCapability(nums: Array[Int], k: Int): Int = {
    min = Int.MaxValue
    pick(nums, nums.indices.toSet, Set.empty, k)
    min
  }

  def pick(
      nums: Array[Int],
      remain: Set[Int],
      picked: Set[Int],
      k: Int
  ): Unit = {
    if (remain.size < k) return
    if (k == 0) {
      // println(picked.toVector)
      val cap = picked.toVector.map(idx => nums(idx)).max
      if (cap < Solution.min) Solution.min = cap
    } else {
      for (r <- remain) {
        val newRemain: Set[Int] = ((remain - r) - (r + 1)) - (r - 1)
        val newPicked = picked + r
        pick(nums, newRemain, newPicked, k - 1)
      }
    }
  }

  @main
  def test(): Unit = {
    println(minCapability(Array(7, 3, 9, 5), 2))
  }

}
