package leetcode.mock23.task1

import scala.util.control.Breaks._

import scala.collection.{mutable => mu}

object Solution {
  def shortestDistance(
      wordsDict: Array[String],
      word1: String,
      word2: String
  ): Int = {

    val nextW1 = mu.Stack.empty[Int]
    val nextW2 = mu.Stack.empty[Int]

    for (i <- (0 until wordsDict.size).reverse) {
      val curs = wordsDict(i)
      if (curs == word1) {
        nextW1.push(i)
      }
      if (curs == word2) {
        nextW2.push(i)
      }
    }

    var minDist = Int.MaxValue
    for (i <- 0 until wordsDict.size) {
      val curs = wordsDict(i)
      if (curs == word1) {
        if (nextW2.nonEmpty) {
          val next = nextW2.top
          val dist = next - i
          if (dist < minDist)
            minDist = dist
        }
        nextW1.pop()
      }
      if (curs == word2) {
        if (nextW1.nonEmpty) {
          val next = nextW1.top
          val dist = next - i
          if (dist < minDist)
            minDist = dist
        }
        nextW2.pop()
      }
    }

    minDist

  }
}
