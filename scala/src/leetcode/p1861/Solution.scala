package leetcode.p1861

import scala.collection.{mutable => mu}

object Solution {
  def rotateTheBox(boxGrid: Array[Array[Char]]): Array[Array[Char]] = {
    transpose(boxGrid.map(x => rowTrans(x)))
    // (boxGrid.map(x => rowTrans(x)))

  }

  def transpose(box: Array[Array[Char]]): Array[Array[Char]] = {
    val xsize = box.size - 1
    val ysize = box(0).size - 1
    val flip = Array.ofDim[Char](box(0).size, box.size)
    for (i <- 0 until box.size) {
      for (j <- 0 until box(0).size) {
        flip(j)(xsize - i) = box(i)(j)
      }
    }
    flip
  }

  def rowTrans(row: Array[Char]): Array[Char] = {
    val fallen = Array.fill[Char](row.size)('.')
    var j = row.size - 1
    for (i <- (0 until row.size).reverse) {
      if (row(i) == '.') {
        // skip j decrease
      } else {
        if (row(i) == '#') {
          fallen(j) = row(i)
          j = j - 1
        } else {
          // should be '*'
          fallen(i) = row(i)
          j = i - 1
        }
      }
    }
    fallen
  }

  @main
  def test(): Unit = {
    // boxGrid = [["#",".","*","."],["#","#","*","."]]
    println(
      rotateTheBox(Array(Array('#', '.', '*', '.'), Array('#', '#', '*', '.')))
        .map(_.mkString(" "))
        .mkString("\n")
    )
  }
}
