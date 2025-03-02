package leetcode.p2673

object Solution {
  def minIncrements(n: Int, cost: Array[Int]): Int = {
    val sums = sum(n, cost)
    val biggest = sums.max
    val maxSums = maxSum(n, sums)
    ???
  }

  def maxSum(n: Int, sums: Array[Int]): Array[Int] = {
    val maxS = Array.fill(n + 1)(0)
    for (i <- 1 until n / 2) {
      maxS(i) = Math.max(sums(i * 2), sums(i * 2 + 1))
    }
    maxS

  }

  def sum(n: Int, cost: Array[Int]): Array[Int] = {
    val sums = Array.fill(n + 1)(0)
    sums(1) = cost(1)
    for (i <- 2 to n) {
      sums(i) = sums(i / 2) + cost(i)
    }
    sums
  }
}
