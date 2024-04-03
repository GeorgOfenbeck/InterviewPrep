package leetcode.p016

object Solution {
    def threeSumClosest(nums: Array[Int], target: Int): Int = {
       val sorted = nums.sorted
       
       var diff = Int.MaxValue
       var closetsum = Int.MaxValue

       for (i <- 0 until sorted.length){
            var left = 0
            var right = sorted.length - 1
            while (left < right){
                if (left == i){
                    left += 1
                } else if (right == i){
                    right -= 1
                } else {
                    val sum = sorted(i) + sorted(left) + sorted(right)
                    if (Math.abs(target - sum) < Math.abs(diff)){
                        diff = Math.abs(target - sum)
                        closetsum = sum
                    }
                    if (sum < target){
                        left += 1
                    } else {
                        right -= 1
                    }
                }
            }
       }
       closetsum
    }
    def main(args: Array[String]): Unit = {
        val nums = Array(-1,2,1,-4)
        val target = 1
        println(threeSumClosest(nums, target))
    }
}