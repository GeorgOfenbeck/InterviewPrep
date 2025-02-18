package leetcode.p213

object Solution {
  def rob(nums: Array[Int]): Int = {

    /** Linear - optimum is either: opt(idx-2) + nums(idx) || opt(idx-1)
      *
      * globally -> with first or without
      */

    val withfirst = run(0, nums)
    val withoutfirst = run(1, nums)
    println(s"withfirst: $withfirst, withoutfirst: $withoutfirst")
    Math.max(withfirst, withoutfirst)

  }
  // from is 0 or 1
  def run(from: Int, nums: Array[Int]): Int = {
    if (nums.size - from <= 0) return 0
    if (nums.size - from == 1) {
      return nums(0 + from)
    }
    if (nums.size - from == 2) {
      return Math.max(nums(0 + from), nums(1 + from))
    }

    if (nums.size == 3) {
      return Math.max(Math.max(nums(0 + from), nums(1 + from)), nums(2 + from))
    }

    var optM2 = nums(from)
    var optM1 = Math.max(nums(from), nums(from + 1))
    var opt = 0

    for (i <- from + 2 until nums.size - 1 + from) {
      opt = getOpt(optM2, optM1, nums(i))
      optM2 = optM1
      optM1 = opt
    }
    return opt

  }

  @inline
  def getOpt(optM2: Int, optM1: Int, x: Int): Int = {
    val withX = optM2 + x
    if (withX > optM1) {
      return withX
    } else {
      optM1
    }
  }

  @main
  def test(): Unit = {
    println(rob(Array(1, 2, 1, 1)))
    println(rob(Array(2, 3, 2)))
    println(rob(Array(1, 2, 3, 1)))
    println(rob(Array(200, 3, 140, 20, 10)))
  }

}
