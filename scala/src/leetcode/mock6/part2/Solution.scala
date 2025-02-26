package leetcode.mock6.part2

object Solution {
  def maxNumberOfApples(weight: Array[Int]): Int = {
    weight.sortBy(x => -x)

    var full = false
    var sum = 0
    var i = 0
    while (!full && i < weight.size) {
      if (sum + weight(i) <= 5000) {
        sum = sum + weight(i)
        i = i + 1
      } else {
        full = true
      }
    }
    i
  }
}
