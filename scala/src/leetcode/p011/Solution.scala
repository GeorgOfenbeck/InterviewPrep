package leetcode.p011

object Solution {
    def maxArea(height: Array[Int]): Int = {
        if (height.length < 2) {
            return 0
        }
        if (height.length == 2) {
            return Math.min(height(0), height(1))
        }

        /* 
        val (l1,l2) = prevMaxLeft(height)
        val (r1,r2) = prevMaxRight(height)
        
        val max2 = Array(l1,l2,r1,r2).sortInPlace().reverse
        println(max2.mkString(","))
        
        if (max2(1) == Int.MinValue) {
            return max2(0)
        } else {    
            return max2(0) +max2(1)
        }*/

        slidingArea(height)
    }

    def slidingArea(height: Array[Int]): Int = {
        var max = 0
        var i = 0
        var j = height.length - 1

        while (i < j) {
            if (height(i) < height(j)) {
                val area = (j - i) * height(i) 
                if (area > max) {
                    max = area
                }
                i += 1
            } else {
                val area = (j - i) * height(j) 
                if (area > max) {
                    max = area
                }
                j -= 1
            }
        }
        max
    }

    
    def prevMaxRight(height: Array[Int]): (Int, Int) = {
        var max = height(0)
        var lastpos = 0
        var a = Int.MinValue
        var b = Int.MinValue
        
        for (i <- 1 until height.length) {
            if (height(i) >= max) {
                max = height(i)
                var area = (i - lastpos) * height(lastpos)
                if (area > a ) {
                    b = a
                    a = area
                } else if (area > b) {
                    b = area
                }
                lastpos = i       
            }
        }
        (a, b)
    }

    def prevMaxLeft(height: Array[Int]): (Int,Int) = {
        var max = height(height.length - 1)
        var lastpos = height.length - 1
        var a = Int.MinValue
        var b = Int.MinValue
        
        for (i <- height.length-2 until 0) {
            if (height(i) > max) {
                max = height(i)
                var area = (lastpos - i) * height(lastpos)
                if (area > a ) {
                    b = a
                    a = area
                } else if (area > b) {
                    b = area
                }
                lastpos = i       
            }
        }
        (a, b)
    }

    def main(args: Array[String]): Unit = {
        println(maxArea(Array(1,8,6,2,5,4,8,3,7))) // 49
        println(maxArea(Array(1,1))) // 1
        println(maxArea(Array(4,3,2,1,4))) // 16
        println(maxArea(Array(1,2,1))) // 2
    }
}
