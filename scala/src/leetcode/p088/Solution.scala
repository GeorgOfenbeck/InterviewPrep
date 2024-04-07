package leetcode.p088


object Solution {
    def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
        if (n == 0) return

        var i = m - 1
        

        var k = n + m - 1
        var j = n - 1

        while (k >= 0){
            if (i >= 0 && j >= 0){
                if (nums1(i) > nums2(j)){
                    nums1(k) = nums1(i)
                    i -= 1
                } else {
                    nums1(k) = nums2(j)
                    j -= 1
                }
            } else if (j >= 0){
                nums1(k) = nums2(j)
                j -= 1
            }
            k -= 1
        }

    }

    def main(args: Array[String]): Unit = {
        val nums1 = Array(1,2,3,0,0,0)
        val nums2 = Array(2,5,6)
        merge(nums1, 3, nums2, 3)
        println(nums1.mkString("[", ", ", "]"))
    }
}