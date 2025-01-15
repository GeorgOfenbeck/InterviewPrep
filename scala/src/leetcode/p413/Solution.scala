package leetcode.p413


object Blub{
    @main
    def main()={
        Solution.numberOfArithmeticSlices(Array(1,2,3,4))
    
    }
}

object Solution {    
    def numberOfArithmeticSlices(nums: Array[Int]): Int = {
        if (nums.size < 3) return 0

        var prev_sum = nums(1) - nums(0)
        var sum = Integer.MAX_VALUE

        var count = 0
        var total = 0

        var isarithmectic = false
        for (i <- 2 until nums.size){
            sum = nums(i) - nums(i-1)
            if (sum == prev_sum ){
                count = count + 1 
            } else {
                total = total + calculatePerms(count+2)
                count = 0                
            }
            prev_sum = sum 
        }
        if (count != 0)
            total = total + calculatePerms(count+2)
        total
    }

    def calculatePerms(length: Int): Int = {
        var mult = 1
        var total = 0
        for(i <- 3 to length){
//            println(i)
            total = total + mult
            mult = mult + 1
        }
        total
    }
}