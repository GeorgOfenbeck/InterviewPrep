package leetcode.p332

import scala.math.Ordering.IntOrdering
import scala.annotation.tailrec

object Solution {
  def coinChange(coins: Array[Int], amount: Int): Int = {
    val coins_dec = coins.sorted(scala.math.Ordering.Int)

    rec(Set.empty, Map(0 -> 0), Vector(0), amount, coins_dec)
  }
  @tailrec
  def rec(
      visted: Set[Int],
      minCoins: Map[Int, Int],
      todo: Vector[Int],
      target: Int,
      coinsDec: Array[Int]
  ): Int = {
    if (todo.isEmpty)
      return -1
    val cur = todo.head
    val sofar = minCoins(cur)

    if (cur == target)
      return sofar

    var newtodo = todo.tail
    var newvisited = visted
    var newmiCoins = minCoins
    for (coin <- coinsDec) {
      if (cur.toLong + coin.toLong < Integer.MAX_VALUE) {
        val sum = cur + coin
        if (!newvisited.contains(sum) && (sum) <= target) {
          newvisited = newvisited + (sum)
          newmiCoins = newmiCoins + ((sum) -> (sofar + 1))
          newtodo = newtodo :+ sum
        }
      }
    }
    rec(newvisited, newmiCoins, newtodo, target, coinsDec)

  }
}

object blub {
  @main
  def main() = {
    val arr = Array(1, 2, 5)
    Solution.coinChange(arr, 11)
  }
}
