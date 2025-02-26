package leetcode.mock17.task2

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def removeOuterParentheses(s: String): String = {

    val sb = new StringBuilder
    var level = 0
    for (i <- 0 until s.size) {
      val cur = s(i)

      if (cur == '(') {
        if (level > 0) {
          sb.append(cur)
        }
        level += 1
      } else {
        level = level - 1
        if (level > 0) {
          sb.append(cur)
        }
      }
    }
    sb.toString()
  }

}
