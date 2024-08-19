package leetcode.p062


object Solution {
    def uniquePaths(m: Int, n: Int): Int = {
       val arr = Array.ofDim[Int](m,n)
       for (j <- 0 until n){
        arr(0)(j) = 1
       }
       for (i <- 0 until m){
        arr(i)(0) = 1
       }
       for (i <- 1 until m){
        for (j <- 1 until n){
            val up = arr(i-1)(j)
            val left = arr(i)(j-1)
            arr(i)(j) = up + left 
        }
       }
       return arr(m-1)(n-1)
    }
}