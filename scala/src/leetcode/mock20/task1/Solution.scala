package leetcode.mock20.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def maxProfit(prices: Array[Int]): Int = {
    if (prices.size == 1) return 0

    val largerFrom: Array[Int] = Array.fill[Int](prices.size)(0)
    val smallerTill: Array[Int] = Array.fill[Int](prices.size)(0)

    smallerTill(0) = prices(0)
    for (i <- 1 until prices.size) {
      if (prices(i) < smallerTill(i - 1)) {
        smallerTill(i) = prices(i)
      } else {
        smallerTill(i) = smallerTill(i - 1)
      }
    }
    largerFrom(prices.size - 1) = prices(prices.size - 1)
    for (i <- (0 until prices.size - 1).reverse) {
      if (prices(i) > largerFrom(i + 1)) {
        largerFrom(i) = prices(i)
      } else {
        largerFrom(i) = largerFrom(i - 1)
      }
    }

    var maxWin = 0

    for (i <- 0 until prices.size) {
      val profit = largerFrom(i) - smallerTill(i)
      if (profit > maxWin)
        maxWin = profit
    }

    maxWin
  }

}
