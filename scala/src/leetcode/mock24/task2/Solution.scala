package leetcode.mock24.task2

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def findUnsortedSubarray(nums: Array[Int]): Int = {

    val cache = mu.Map.empty[(Int, Int), Int]

    var biggestStack = mu.Stack.empty[(Int, Int)]
    var smallestStack = mu.Stack.empty[(Int, Int)]

    biggestStack.push((0, nums(0)))
    for (i <- 1 until nums.size) {
      if (biggestStack.top._2 <= nums(i)) {
        biggestStack.push((i, nums(i)))
      }
    }
    smallestStack.push((nums.size - 1, nums(nums.size - 1)))
    for (j <- (0 until nums.size - 1).reverse) {
      if (smallestStack.top._2 >= nums(j)) {
        smallestStack.push((j, nums(j)))
      }
    }

    var i = 0

    while (smallestStack.nonEmpty) {
      val (curidx, curval) = smallestStack.top
      if (nums(i) != curval) {
        smallestStack = mu.Stack.empty[(Int, Int)]
      } else {
        smallestStack.pop()
        i = i + 1
      }
    }

    var j = nums.size - 1

    while (biggestStack.nonEmpty) {
      val (curidx, curval) = biggestStack.top
      if (nums(j) != curval) {
        biggestStack = mu.Stack.empty[(Int, Int)]
      } else {
        biggestStack.pop()
        j = j - 1
      }
    }
    if (j < i) return 0 else (j - i) + 1
  }
}
