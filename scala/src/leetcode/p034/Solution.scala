package leetcode.p034

object Solution {
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    if (nums.isEmpty) Array(-1, -1)
    else
      Array(
        binsearch(nums, 0, nums.size - 1, target, None, true),
        binsearch(nums, 0, nums.size - 1, target, None, false)
      )
  }

  // increasing
  def binsearch(
      nums: Array[Int],
      l: Int,
      r: Int,
      target: Int,
      found: Option[Int],
      left: Boolean
  ): Int = {
    val m = l + (r - l) / 2
    val mid = nums(m)
    if (l >= r) {
      if (mid != target) {
        found match {
          case None        => return -1
          case Some(value) => value
        }
      } else return m
    } else {
      if (mid == target) {
        if (left) {
          binsearch(nums, l, m - 1, target, Some(m), left)
        } else {
          binsearch(nums, m + 1, r, target, Some(m), left)
        }
      } else {
        if (mid > target) {
          binsearch(nums, l, m - 1, target, found, left)
        } else {
          binsearch(nums, m + 1, r, target, found, left)
        }
      }
    }
  }
}

object XX {
  @main
  def main() = {
    val nums = Array(0,0,0,1,2,3)
    val res = Solution.searchRange(nums, 0)
    res.map(println)
  }
}
