package leetcode.mock32.task1

object Solution {
  def sortedSquares(nums: Array[Int]): Array[Int] = {
    val neg = nums.takeWhile(p => p < 0).map(p => p * p).reverse
    val pos = nums.dropWhile(p => p >= 0).map(p => p * p)

    val res = Array.ofDim[Int](nums.size)

    var i = 0
    var j = 0

    while (i < neg.size && j < pos.size) {
      if (neg(i) < pos(j)) {
        res(i + j) = neg(i)
        i += 1
      } else {
        res(i + j) = pos(j)
        j += 1
      }
    }
    while (i < neg.size) {
      res(i + j) = neg(i)
      i += 1
    }
    while (j < neg.size) {
      res(i + j) = pos(j)
      j += 1
    }
    res
  }
}
