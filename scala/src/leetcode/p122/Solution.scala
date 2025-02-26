package leetcode.p122

import scala.collection.{mutable => mu}

object Solution {
  def maxProfit(prices: Array[Int]): Int = {

    // get smallest from Start to i
    // profit = i - smallest
    // totalprofit = profit + maxProfit(i+1, prices)

    maxProfit(0, prices, mu.Map.empty[Int, Int])
  }

  def maxProfit(
      start: Int,
      prices: Array[Int],
      cache: mu.Map[Int, Int]
  ): Int = {
    if (cache.contains(start)) {
      return cache(start)
    }
    var max = 0
    for (i <- (start until prices.length).reverse) {
      val profit = prices(i) - prices(start)
      val totalProfit = profit + maxProfit(i + 1, prices, cache)
      if (totalProfit > max) {
        max = totalProfit
      }
    }
    cache.put(start, max)
    max
  }

  @main
  def test(): Unit = {
    // [6,1,3,2,4,7]

    println(maxProfit(Array(7, 1, 5, 3, 6, 4)))
  }
}
