package leetcode.p240

import scala.annotation.tailrec


object Solution {
    def searchMatrix(matrix: Array[Array[Int]], target: Int): Boolean = {
        binsearch(0,matrix.size, 0, matrix(0).size, matrix, target) 
    }
    
    def binsearch(l: Int, r: Int, u: Int, d: Int, matrix: Array[Array[Int]], target: Int): Boolean = {
        if(r <= l || d <= u){
            return false
        } else{
            val idx = l + (r - l - 1)/2
            val idy = u + (d - u - 1)/2

            val check = matrix(idx)(idy)

            if (check == target){
                return true
            } else {
                if (check < target){
                    binsearch(idx + 1 , r, idy + 1, d, matrix, target) ||
                    binsearch(l, idx + 1, idy + 1, d, matrix, target) ||
                    binsearch(idx + 1, r, u, idy + 1, matrix, target )
                } else{
                    binsearch(l, idx, u, idy, matrix, target) ||
                    binsearch(idx, r, u, idy, matrix, target) ||
                    binsearch(l, idx, idy, d, matrix, target) 
                }
            }
        }
    }
}


object test{
    @main
    def main() = {
        val m = Array(Array(1), Array(3), Array(5))
        //val m = Array(Array(1,4,7,11,15),Array(2,5,8,12,19),Array(3,6,9,16,22),Array(10,13,14,17,24),Array(18,21,23,26,30))
        println(Solution.searchMatrix(m, 1))
    }

}

