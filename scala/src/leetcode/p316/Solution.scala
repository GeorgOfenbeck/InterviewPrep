package leetcode.p316

import scala.collection.{mutable => mu}

object Solution {
  def removeDuplicateLetters(s: String): String = {

    val stack = mu.Stack.empty[Char]

    val seen = mu.Set.empty[Char]

    val last = (0 until s.size).foldLeft(Map.empty[Char, Int]) { (acc, ele) =>
      {
        acc + (s(ele) -> ele)
      }
    }

    for (i <- 0 until s.size) {
      val c = s(i)
      if (!seen.contains(c)) {
        while (stack.nonEmpty && stack.top > c && last(stack.top) > i) {
          seen.remove(stack.pop())
        }
        seen.add(c)
        stack.push(c)
      }
    }
    val sb = new StringBuilder
    for (s <- stack) sb.append(s)
    sb.toString()
  }
}
