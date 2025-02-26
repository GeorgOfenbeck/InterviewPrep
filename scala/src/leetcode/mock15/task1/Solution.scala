package leetcode.mock15.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def licenseKeyFormatting(s: String, k: Int): String = {
    // [a-Z][0-9][\-]
    //
    // each group => k chars
    // first group => < k chars && > 0  ==> rest
    // dash between groups
    // lowercase => uppercase

    val reverseString = s.toList.reverse
    val preTrim = split(List.empty, 0, k, reverseString)
    preTrim.dropWhile(_ == '-').mkString

  }

  @tailrec
  def split(
      sofar: List[Char],
      i: Int,
      k: Int,
      input: List[Char]
  ): List[Char] = {

    if (input.isEmpty) return sofar

    if (i == k) { // every k chars add dash
      split('-' +: sofar, 0, k, input)
    } else {
      val cur = input.head.toUpper
      if (cur == '-') {
        split(sofar, i, k, input.tail) // ignore original dash
      } else {
        split(cur +: sofar, i + 1, k, input.tail)
      }
    }
  }

}
