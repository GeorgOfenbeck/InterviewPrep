package leetcode.p202

/*A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
*/


object Solution {
    def isHappy(n: Int): Boolean = {
       val set = scala.collection.mutable.Set.empty[Int]            
       
       if (n == 1) return true
       else {
            var num = n
            while (!set.contains(num))    {
                set.add(num)
                num = squareofDigits(num)
            } 
            if (num == 1) return true else false
       }
       
    }

    def squareofDigits(n: Int): Int = {
        var remain = n
        var digits = List.empty[Int]
        while (remain > 0){
            val cur = remain % 10
            digits = (cur * cur) +: digits
            remain = remain / 10
        }
        digits.sum
    }
}
