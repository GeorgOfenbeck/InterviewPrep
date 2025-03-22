package leetcode.mock34.task1

object Solution {
  def reverseVowels(s: String): String = {
    reverseVowels2(s)
  }
  def reverseVowels2(sorg: String): String = {
    val s = sorg.toCharArray
    var i = 0
    var j = s.size - 1

    while (i < j) {
      (isVowel(s(i)), isVowel(s(j))) match {
        case (true, true) => {
          val tmp = s(i)
          s(i) = s(j)
          s(j) = tmp
          i += 1
          j -= 1

        }
        case (false, false) => {
          i += 1
          j -= 1
        }
        case (true, false) => i += 1
        case (false, true) => j -= 1
      }
    }
    s.mkString
  }

  def isVowel(c: Char): Boolean = {
    c match {
      case 'a' | 'e' | 'i' | 'o' | 'u' | 'A' | 'E' | 'I' | 'O' | 'U' => true
      case _                                                         => false
    }
  }
}
