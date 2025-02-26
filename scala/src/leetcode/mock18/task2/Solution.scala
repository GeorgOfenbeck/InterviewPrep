package leetcode.mock18.task2

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {

  // def totalFruit(fruits: Array[Int]): Int = {
  //
  //   if (fruits.size == 0) {
  //     return 0
  //   }
  //   if (fruits.size == 1) {
  //     return 1
  //   }
  //   if (fruits.size == 2) {
  //     return 2
  //   }
  //
  //   var fruitSet = Map.empty[Int, Int]
  //
  //   fruitSet += (fruits(1) -> 1)
  //   fruitSet += (fruits(0) -> 0)
  //
  //   var max = 0
  //   var currentMax = 2
  //
  //   var lastFruit = fruits(1)
  //   for (i <- 2 until fruits.size) {
  //     if (fruitSet.contains(fruits(i))) {
  //       if (lastFruit != fruits(i)) {
  //         lastFruit = fruits(i)
  //       }
  //     } else { // one of the fruits is different
  //
  //       if (fruitSet.size == 1) {
  //         fruitSet += (fruits(i) -> i)
  //       } else {
  //         val prev = fruits(i)
  //
  //
  //       }
  //     }
  //   }
  // }
  def totalFruit2(fruits: Array[Int]): Int = {

    if (fruits.size == 0) {
      return 0
    }
    if (fruits.size == 1) {
      return 1
    }
    if (fruits.size == 2) {
      return 2
    }

    var fruitSet = Map.empty[Int, Int]

    fruitSet += (fruits(1) -> 1)
    fruitSet += (fruits(0) -> 0)
    var max = 0
    var currentMax = 2

    for (i <- 2 until fruits.size) {
      if (fruitSet.contains(fruits(i))) {
        // skip
      } else { // one of the fruits is different
        if (fruitSet.size == 1) {
          fruitSet += (fruits(i) -> i)
        } else {
          val a = fruitSet.keys.head
          val b = fruitSet.keys.last
          val (older, newer) = if (fruitSet(a) < fruitSet(b)) (a, b) else (b, a)

          val prev = fruits(i - 1)

          if (prev == a) {
            val dist = i - fruitSet.values.max
            if (dist > max)
              max = dist
            fruitSet -= b
            fruitSet += (fruits(i) -> i)
          } else {
            val dist = i - fruitSet.values.max
            if (dist > max)
              max = dist
            fruitSet -= a
            fruitSet += (fruits(i) -> i)
          }
        }
      }
    }
    val distA = (fruits.size - fruitSet.values.head)
    val distB = (fruits.size - fruitSet.values.last)
    return Vector(max, distA, distB).max
  }
}
