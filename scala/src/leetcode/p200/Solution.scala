
package leetcode.p200

object Solution {
    def numIslands(grid: Array[Array[Char]]): Int = {
       var max = 0

         for (i <- 0 until grid.length) {
              for (j <- 0 until grid(i).length) {
                if (grid(i)(j) == '1') {
                    if (i )
                    grid(i)(j) = max.toChar     
                }
              }
         }
    }
}