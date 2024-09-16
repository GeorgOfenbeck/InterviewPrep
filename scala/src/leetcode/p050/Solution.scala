package leetcode.p050

import scala.annotation.tailrec


object Solution {
    def myPow(x: Double, n: Int): Double = {
        if (n == 0) return 1
        if (n > 0) return powrec(x, n)
        else { return powrecneg(x, n)
        }
    }




    def powrecneg(x: Double, n: Int): Double = {
        if (n == -1) return 1/x
        if(n % 2 == 0){
            val res = powrecneg(x, n/2) 
            res * res 
        } else{
            val res = powrecneg(x, n/2)
            res * res * 1/x 
        }
    }

    def powrec(x: Double, n: Int): Double = {
        if (n == 1) return x
        if(n % 2 == 0){
            val res = powrec(x, n/2)
            res * res 
        } else{
            val res = powrec(x, n/2) 
            res * res * x
        }
    }    
}


object blub{
    @main
    def main()={
        println(Solution.myPow(2,-2))
    }
}