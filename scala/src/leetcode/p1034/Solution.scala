package leetcode.p1034

import scala.collection.{mutable => mu}

object Solution {
  def colorBorder(
      grid: Array[Array[Int]],
      row: Int,
      col: Int,
      color: Int
  ): Array[Array[Int]] = {
    val all = mu.Set.empty[(Int, Int)]
    val inner = mu.Set.empty[(Int, Int)]

    all.add((row, col))
    rec(grid, row, col, all, inner)
    val border = all.diff(inner)
    border.foreach { case (i, j) =>
      grid(i)(j) = color
    }
    grid
  }

  def rec(
      grid: Array[Array[Int]],
      i: Int,
      j: Int,
      visited: mu.Set[(Int, Int)],
      inner: mu.Set[(Int, Int)]
  ): Unit = {

    if (neighAreSame(i, j, grid)) {
      inner.add((i, j))
    }
    // above
    checkRec(grid, i, j - 1, grid(i)(j), visited, inner)
    checkRec(grid, i - 1, j, grid(i)(j), visited, inner)
    checkRec(grid, i, j + 1, grid(i)(j), visited, inner)
    checkRec(grid, i + 1, j, grid(i)(j), visited, inner)

  }

  def checkRec(
      grid: Array[Array[Int]],
      i: Int,
      j: Int,
      sofar: Int,
      visited: mu.Set[(Int, Int)],
      inner: mu.Set[(Int, Int)]
  ): Unit = {
    if (isValid(i, j, grid) && grid(i)(j) == sofar) {
      if (!visited.contains((i, j))) {
        visited.add((i, j))
        rec(grid, i, j, visited, inner)
      }
    }

  }

  def isValid(i: Int, j: Int, grid: Array[Array[Int]]): Boolean = {

    if (i < 0 || i == grid.length || j < 0 || j == grid(0).length) { // we outside the border
      return false
    } else return true
  }

  def neighAreSame(i: Int, j: Int, grid: Array[Array[Int]]): Boolean = {
    if (i == 0 || i == grid.length - 1 || j == 0 || j == grid(0).length - 1) { // we are at the border
      return false
    } else {
      val above = grid(i)(j - 1)
      val left = grid(i - 1)(j)
      val down = grid(i)(j + 1)
      val right = grid(i + 1)(j)

      if (
        above == left && left == down && down == right && right == grid(i)(j)
      ) {
        return true
      } else {
        return false
      }
    }
  }
}
