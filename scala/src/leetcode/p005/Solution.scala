package leetcode.p005


object Solution {
    def longestPalindrome(s: String): String = {
        if (s.length == 0) {
            return ""
        }
        var max = 0
        var j = 0
        var start = 0
        var end = 0
        
        for (i <- 0 until s.length) {
            var len1 = longestPalindromeMid(s, i)
            var len2 = longestPalindromeDoubleMid(s, i)
            var len = Math.max(len1, len2)
            if (len > max) {
                max = len
                j = i
            }
        }


        val len = max
        if (len % 2 == 0) {
            start = j - (len / 2) + 1
            end = j + len / 2 
        } else {
            start = j - len / 2
            end = j + len / 2
        }
        return s.substring(start, end + 1)
    }



    def longestPalindromeMid(s: String, i: Int): Int = {
       var size = 0
       var left = i
       var right = i
        while (left >= 0 && right < s.length && s(left) == s(right)) {
              size = right - left + 1
              left -= 1
              right += 1
        }
        return size 
    }

    def longestPalindromeDoubleMid(s: String, i: Int): Int = {
        var size = 0
        var left = i
        var right = i + 1
        while (left >= 0 && right < s.length && s(left) == s(right)) {
            size = right - left + 1 
            left -= 1
            right += 1
        }
        return size 
    }


    def isPalindrome(s: String): Boolean = {
        var i = 0
        var j = s.length - 1
        while (i < j) {
            if (s(i) != s(j)) {
                return false
            }
            i += 1
            j -= 1
        }
        return true
    }

    def main(args: Array[String]): Unit = {
        println(longestPalindrome("cbbd"))
    }
}