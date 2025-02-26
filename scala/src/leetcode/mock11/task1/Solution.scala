package leetcode.mock11.task1

import scala.collection.{mutable => mu}

object Solution {
  def reverseString(s: Array[Char]): Unit = {
    var i = 0
    var j = s.size - 1

    while (i < j) {
      val tmp = s(i)
      s(i) = s(j)
      s(j) = tmp
      i = i + 1
      j = j - 1
    }

  }
}
