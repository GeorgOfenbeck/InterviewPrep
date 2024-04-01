package leetcode.p1119


object Solution {
    def removeVowels(s: String): String = {
      val sb = new StringBuilder
      for (c <- s) {
        if (!"aeiou".contains(c)) {
          sb.append(c)
        }
      }
      sb.toString   
    }
}