package leetcode.p171


object Solution {
    def titleToNumber(columnTitle: String): Int = {
        var num = 0
        val base = 26
        for(i <- 0 until columnTitle.size){
            val c = columnTitle(columnTitle.size-1-i)
            val digit = Char2Num(c)
            num = num + scala.math.pow(base, i).intValue() * digit
        }
        num 
    }

    @inline 
    def Char2Num(c: Char): Int ={
        return c - 'A' + 1 
    }
}