package leetcode.mock7.task1

object Solution {
  def heightChecker(heights: Array[Int]): Int = {
    val sorted = heights.sorted
    var count = 0
    for (i <- 0 until heights.size) {
      if (heights(i) != sorted(i))
        count = count + 1
    }
    count
  }
}
