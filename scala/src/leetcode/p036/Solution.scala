package leetcode.p036
import scala.util.boundary, boundary.break


object Solution {
  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    val rows = Array[Set[Char]](
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set()
    )
    val cols = Array[Set[Char]](
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set()
    )
    val boxes = Array[Set[Char]](
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set(),
      Set()
    )

    boundary[Boolean] {
      for (i <- 0 until 9) {
        for (j <- 0 until 9) {
          val c = board(i)(j)
          if (c != '.') {
            if (rows(i).contains(c) || cols(j).contains(c) || boxes(i / 3 * 3 + j / 3).contains(c))
                break(false) 
            
            rows(i) += c
            cols(j) += c
            boxes(i / 3 * 3 + j / 3) += c
          }
        }
      }
      true
    }
    }

  def main(): Unit = {}
}
