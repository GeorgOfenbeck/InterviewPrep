package leetcode.mock3.task2

object Solution {
  def countAndSay(n: Int): String = {
    rec(n)
  }

  def rec(n: Int): String = {
    if (n == 1) return "1"
    else runLengthEncoding(rec(n - 1))
  }

  def runLengthEncoding(in: String): String = {
    val sb = new StringBuilder
    var prev = in(0)
    var len = 0
    for (i <- 0 until in.size) {
      val cur = in(i)
      if (cur == prev) {
        len = len + 1
      } else {
        len = len + 1
        sb.append(len.toString() + prev)
        prev = cur
        len = 0
      }
    }
    len = len + 1
    sb.append(len.toString() + prev)
    sb.toString()
  }

}
