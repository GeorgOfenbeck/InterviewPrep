package leetcode.mock19.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def rotatedDigits(n: Int): Int = {
    (1 to n).map(num => rotate(num)).flatten.size
  }

  def rotate(n: Int): Option[Int] = {

    val digits: Vector[Int] = n.toString.map(_.asDigit).toVector

    // go through each digit -> rotate => abort if any digit is invalid

    val transformed = digits.map(digit => transformDigit(digit)).flatten

    if (transformed.isEmpty) return None
    if (transformed.size != digits.size) return None

    val res = transformed.foldLeft(0)((acc, digit) => acc * 10 + digit)

    if (res == n) None else Some(res)

  }

  def transformDigit(n: Int): Option[Int] = {
    n match {
      case 0 => Some(0)
      case 1 => Some(1)
      case 2 => Some(5)
      case 3 => None
      case 4 => None
      case 5 => Some(2)
      case 6 => Some(9)
      case 7 => None
      case 8 => Some(8)
      case 9 => Some(6)
      case _ => assert(false, s"Invalid digit: $n"); None
    }
  }
}
