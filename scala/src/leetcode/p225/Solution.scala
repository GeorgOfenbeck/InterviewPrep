package leetcode.p225

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def verifyPreorder(preorder: Array[Int]): Boolean = {

    val upper = mu.Stack.empty[Int]
    val lower = mu.Stack.empty[Int]

    upper.push(Int.MaxValue)
    lower.push(Int.MinValue)

    var correct = true
    breakable {
      for (i <- 0 until preorder.size) {
        val cur = preorder(i)
        if (cur < upper.top) {
          if (cur < lower.top) {
            correct = false
            break
          } else {
            upper.push(cur)
          }
        } else {
          while (cur > upper.top) {
            lower.push(upper.pop())
          }
          if (cur > lower.top && cur < upper.top) {
            upper.push(cur)
          } else {
            correct = false
            break
          }
        }
      }
    }

    return correct
  }
}
