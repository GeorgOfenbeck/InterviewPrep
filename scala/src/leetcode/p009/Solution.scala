package leetcode.p009

object Solution {
  def isPalindrome(x: Int): Boolean = {
    if (x < 0) {
      false
    } else {
      val inverted = invert(x, 0)
      x == inverted
    }
  }

  @scala.annotation.tailrec
  def invert(remain: Int, acc: Int): Int = {
    if (remain == 0) {
      acc
    } else {
      val digit = remain % 10
      invert(remain / 10, acc * 10 + digit)
    }
  }

}
