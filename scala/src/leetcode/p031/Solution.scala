package leetcode.p031


object Solution {
    def nextPermutation(nums: Array[Int]): Unit = {
        var i = nums.length - 2
        while (i >= 0 && nums(i) >= nums(i + 1)) {
            i -= 1
        }
        if (i >= 0){
            var j = nums.length - 1
            while (nums(j) <= nums(i)) {
                j -= 1
            }
            swap(nums, i, j)    
        }    
        reverse(nums, i + 1)
    }


    def reverse(nums: Array[Int], start: Int): Unit = {
        var i = start
        var j = nums.length - 1
        while (i < j) {
            swap(nums, i, j)
            i += 1
            j -= 1
        }
    }

    def swap(nums: Array[Int], i: Int, j: Int): Unit = {
        val tmp = nums(i)
        nums(i) = nums(j)
        nums(j) = tmp
    }
}