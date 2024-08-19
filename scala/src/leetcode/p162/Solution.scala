package leetcode.p162

import scala.annotation.tailrec

object Solution {
  def findPeakElement(nums: Array[Int]): Int = {
    rec(0, nums.size - 1, nums)

  }

  @tailrec
  def rec(leftsmaller: Int, rightsmaller: Int, nums: Array[Int]): Int = {
    val middle = leftsmaller + (rightsmaller - leftsmaller) / 2
    val mid = nums(middle)

    val midm1 = if (middle == 0) Int.MinValue else nums(middle - 1)

    if (midm1 > mid) {
      rec(leftsmaller, middle - 1, nums)
    } else {
      val midp1 =
        if (middle == nums.size - 1) Int.MinValue else nums(middle + 1)
      if (midp1 > mid) {
        rec(middle + 1, rightsmaller, nums)
      } else {
        return middle
      }
    }

  }
}


object XX{
    @main
    def main()={
        val nums = Array(5,4,6)
        println(Solution.findPeakElement(nums))
    }
}
