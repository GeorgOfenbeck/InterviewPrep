package leetcode.mock21.task1

object Solution {
  def romanToInt(s: String): Int = {

    val digits = (0 until s.size).map { i =>
      val cur = s(i)
      val next: Option[Char] = if (i == s.size - 1) None else Some(s(i + 1))
      romanDigitToInt(cur, next)
    }
    digits.reduce(_ + _)
  }

  def romanDigitToInt(c: Char, next: Option[Char]): Int = {
    c match {
      case 'I' =>
        next match {
          case Some('V') => -1
          case Some('X') => -1
          case None      => 1
          case _         => assert(false)

        }
      case 'V' => 5
      case 'X' =>
        next match {
          case Some('L') => -10
          case Some('C') => -10
          case None      => 10
          case _         => assert(false)
        }

      case 'L' => 50
      case 'C' =>
        next match {
          case Some('D') => -100
          case Some('M') => -100
          case None      => 100
          case _         => assert(false)
        }
      case 'D' => 500
      case 'M' => 1000
      case _   => assert(false)
    }
  }
}
