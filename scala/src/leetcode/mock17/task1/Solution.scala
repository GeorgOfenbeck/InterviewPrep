package leetcode.mock17.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def divisorGame(n: Int): Boolean = {
    turnA(n, mu.Map.empty[Int, Boolean], mu.Map.empty[Int, Boolean])
  }

  def turnA(
      n: Int,
      cacheA: mu.Map[Int, Boolean],
      cacheB: mu.Map[Int, Boolean]
  ): Boolean = {
    if (cacheA.contains(n)) {
      return cacheA(n)
    } else {
      val divisors = getDivisors(n)
      val res = divisors.exists(d => !turnB(n - d, cacheA, cacheB))
      cacheA.update(n, res)
      res
    }
  }

  def turnB(
      n: Int,
      cacheA: mu.Map[Int, Boolean],
      cacheB: mu.Map[Int, Boolean]
  ): Boolean = {
    if (cacheB.contains(n)) {
      return cacheB(n)
    } else {
      val divisors = getDivisors(n)
      val res = divisors.exists(d => !turnA(n - d, cacheA, cacheB))
      cacheB.update(n, res)
      res
    }
  }

  def getDivisors(n: Int): List[Int] = {
    val divisors = mu.ListBuffer[Int]()
    for (i <- 1 until n) {
      if (n % i == 0) {
        divisors.append(i)
      }
    }
    divisors.toList
  }
  @main
  def test(): Unit = {
    val n = 3
    val res = divisorGame(n)
    println(res)
  }
}
