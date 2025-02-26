package leetcode.mock5.task1
// To execute Scala code, please define an object named Solution that extends App
object Solution {
  def removeElement(nums: Array[Int], `val`: Int): Int = {
    val value = `val`

    val without = nums.filter(p => p != value)

    for (i <- 0 until without.size) {
      nums(i) = without(i)
    }
    without.size
  }
}
