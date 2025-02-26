package leetcode.mock10.task2

import scala.annotation.tailrec
import scala.collection.{mutable => mu}

object Solution {
  def repeatedSubstringPattern(s: String): Boolean = {

    val head = s.head

    // get possible divisors
    val divisors = getDivisors(s, head)
    check(s, divisors)
  }

  def repeat(s: String, times: Int): String = {
    val sb = new StringBuilder
    for (i <- 0 until times) sb.append(s)
    s.toString
  }

  @tailrec
  def check(s: String, possible: List[String]): Boolean = {
    if (possible.isEmpty) return false
    else {
      val cur = possible.head
      val div = s.size / cur.size
      if (s == repeat(cur, div)) return true
      else check(s, possible.tail)
    }
  }

  def getDivisors(s: String, head: Char): List[String] = {
    var res = List.empty[String]
    val sb = new StringBuilder
    sb.append(head)
    // todo  -> only till half
    for (i <- 1 until s.size) {
      if (s(i) == head) {
        if ((s.size / sb.size) * sb.size == s.size) // we devide
          res = sb.toString :: res
      }
      sb.append(s(i))
    }
    res
  }
}
