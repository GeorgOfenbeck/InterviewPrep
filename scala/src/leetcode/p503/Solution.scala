package leetcode.p503

import scala.collection.{mutable => mu}

object Solution {
  def nextGreaterElements(nums: Array[Int]): Array[Int] = {

    val ng = Array.fill(nums.length)(-1)
    val stack = mu.Stack.empty[Int]

    for (k <- 1 to 2)
      for (i <- 0 until nums.size) {
        while (stack.nonEmpty && nums(stack.top) < nums(i)) {
          ng(stack.pop()) = nums(i)
        }
        stack.push(i)
      }
    ng
  }

  @main
  def test(): Unit = {
    // [1,2,3,4,3]
    println(nextGreaterElements(Array(1, 2, 3, 4, 3)).mkString(","))
  }
}
