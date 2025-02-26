package leetcode.mock10.task1

import scala.collection.{mutable => mu}

object Solution {
  def findPeakElement(nums: Array[Int]): Int = {
    if (nums.size == 1) return 0
    rec(nums, 0, nums.size - 1)
  }

  def rec(nums: Array[Int], i: Int, j: Int): Int = {

    val mididx = (i + j) / 2
    val left = if (mididx - 1 >= 0) nums(mididx - 1) else Int.MinValue
    val right = if (mididx + 1 < nums.length) nums(mididx + 1) else Int.MinValue
    val mid = nums(mididx)
    println(
      s"i: $i, j: $j, mididx: $mididx, left: $left, mid: $mid, right: $right"
    )
    if (left < mid && right < mid) return mididx

    if (left > mid) {
      rec(nums, i, mididx - 1)
    } else {
      rec(nums, mididx + 1, j)
    }

  }
}
