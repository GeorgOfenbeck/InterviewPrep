package leetcode.p046

object Solution {
  def permute(nums: Array[Int]): List[List[Int]] = {
    val s = nums.toSet
    rec(s, List.empty, List.empty)
  }

  def rec(
      nums: Set[Int],
      sofar: List[Int],
      res: List[List[Int]]
  ): List[List[Int]] = {
    var newres = res
    if (nums.isEmpty) {
      return sofar +: res
    }
    for (n <- nums) { // but ea
      val setwithout = nums - n
      newres = rec(setwithout, n +: sofar, newres)
    }
    newres
  }
}
