package leetcode.mock30.task2

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
class BinaryMatrix {
  def get(row: Int, col: Int): Int = ???
  def dimensions(): Array[Int] = ???
}

import scala.annotation.tailrec

object Solution {
  def leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int = {
    val dim = binaryMatrix.dimensions()
    val (rows, cols) = (dim(0), dim(1))

    val maxSeen: Option[Int] = None

    (0 until rows)
      .foldLeft(maxSeen)((acc, ele) => {
        val right = acc.getOrElse(cols - 1)
        val nSeen = findLeftMostOne(binaryMatrix, ele, 0, right, None)
        Some(nSeen.getOrElse(right))
      })
      .getOrElse(-1)

  }

  @tailrec
  def findLeftMostOne(
      binaryMatrix: BinaryMatrix,
      row: Int,
      left: Int,
      right: Int,
      found: Option[Int]
  ): Option[Int] = {
    if (left > right) return found
    val mididx = left + (right - left) / 2
    binaryMatrix.get(row, mididx) match {
      case 0 => findLeftMostOne(binaryMatrix, row, mididx + 1, right, found)
      case 1 => {
        val nfound = found
          .map(prev => if (prev < mididx) prev else mididx)
          .getOrElse(mididx)
        findLeftMostOne(binaryMatrix, row, left, mididx - 1, found)
      }
    }

  }
}
