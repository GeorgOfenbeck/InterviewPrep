package leetcode.mock1.Task2

object Solution {
  def findDisappearedNumbers(nums: Array[Int]): List[Int] = {
    val numset = nums.toSet

    val nat = for (i <- 1 to nums.size) yield i
    val nset = nat.toSet
    nset.diff(numset).toList
  }
}
