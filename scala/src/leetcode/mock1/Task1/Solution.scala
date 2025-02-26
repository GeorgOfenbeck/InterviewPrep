package leetcode.mock1.Task1

object Solution {
  def isPowerOfTwo(n: Int): Boolean = {
    if (n == 0) return false

    var x: Int = n
    var count = 0

    while (x != 0) {
      if ((x & 1) == 1) {
        count = count + 1
      }
      x = x / 2
    }
    count == 1
  }
}
