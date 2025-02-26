package leetcode.mock19.task2

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def maxDistToClosest(seats: Array[Int]): Int = {
    val fromLeft = Array.fill(seats.length)(0)
    val fromRight = Array.fill(seats.length)(0)

    // sweep from left
    for (i <- 0 until seats.size) {

      if (seats(i) == 1) { // taken
        fromLeft(i) = 0
      } else {
        if (i == 0) {
          fromLeft(i) = Int.MaxValue
        } else {
          if (fromLeft(i - 1) == Int.MaxValue) {
            fromLeft(i) = Int.MaxValue
          } else {
            fromLeft(i) = fromLeft(i - 1) + 1
          }
        }
      }
    }

    // sweep from right
    for (i <- (0 until seats.size).reverse) {

      if (seats(i) == 1) { // taken
        fromRight(i) = 0
      } else {
        if (i == seats.size - 1) {
          fromRight(i) = Int.MaxValue
        } else {
          if (fromRight(i + 1) == Int.MaxValue) {
            fromRight(i) = Int.MaxValue
          } else {
            fromRight(i) = fromRight(i + 1) + 1
          }
        }
      }
    }

    var maxDistance = 0
    for (i <- (0 until seats.size)) {
      // take min toleft/toRight <- max of that

      val dist = Math.min(fromLeft(i), fromRight(i))
      if (dist > maxDistance) {
        maxDistance = dist
      }
    }
    maxDistance

  }
}
