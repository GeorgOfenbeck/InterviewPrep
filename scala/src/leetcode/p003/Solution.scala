package leetcode.p003

object Solution {

  @main
  def main() = {
    println(lengthOfLongestSubstring("baab!bb"))
  }

  def lengthOfLongestSubstring(s: String): Int = {

    var current = scala.collection.mutable.Set.empty[Char]

    var max = 0

    var i = 0
    var j = 0

    while (i < s.length()) {
      val ch = s.charAt(i)

      if (!current.contains(ch)) {
        current.add(ch)
        if (current.size > max)
            max = current.size 
      } else {
        while (s.charAt(j) != ch){
            val prev = s.charAt(j)
            current.remove(prev)
            j = j+1
        }
        j = j + 1
      }
      i = i + 1
    }


    max
  }
}
