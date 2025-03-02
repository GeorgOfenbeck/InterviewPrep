package leetcode.mock25.task1

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def rob(nums: Array[Int]): Int = {
    if (nums.isEmpty) return 0

    if (nums.size == 1) return nums(0)
    if (nums.size == 2) return Math.max(nums(0), nums(1))
    val cache = Array.fill[Int](nums.size)(-1)

    cache(nums.size - 1) = nums(nums.size - 1)
    cache(nums.size - 2) = Math.max(nums(nums.size - 1), nums(nums.size - 2))

    for (i <- (0 until nums.size - 2).reverse) {
      val start = i
      cache(i) = Math.max(nums(start) + cache(start + 2), cache(start + 1))
    }
    cache(0)
  }
}
