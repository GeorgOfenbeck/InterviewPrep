package leetcode.p2556

import scala.collection.{mutable => mu}

object Solution {
  def isPossibleToCutPath(grid: Array[Array[Int]]): Boolean = {

    val paths = Array.ofDim[Boolean](grid.size, grid(0).size)

    val cleaned = Array.ofDim[Int](grid.size, grid(0).size)

    for (i <- 0 until grid.length) {
      for (j <- 0 until grid(0).length) {
        if (grid(i)(j) == 1) {
          val left = if (j != 0) {
            paths(i)(j - 1)
          } else {
            if (i == 0) true else false
          }
          val right = if (i != 0) {
            paths(i - 1)(j)
          } else {
            false
          }

          paths(i)(j) = left || right
        }
      }
    }
    if (paths.last.last == false) {
      return true
    }

    for (i <- (0 until paths.size).reverse) {
      for (j <- (0 until paths(0).size).reverse) {
        if (paths(i)(j) == true) {
          val right = if (j == paths(0).size - 1) {
            if (i == paths.size - 1) true else false
          } else {
            paths(i)(j + 1)
          }
          val down = if (i == paths.size - 1) false else paths(i + 1)(j)
          paths(i)(j) = right || down
        }
      }
    }

    val maxdist = grid.size + grid(0).size - 2

    var needle = false
    for (d <- 1 until maxdist) {
      var count = 0
      for (i <- 0 to d) {
        val j = d - i
        if (i < grid.size && j >= 0 && j < grid(0).size)
          if (paths(i)(j) == true) {
            count = count + 1
          }
      }
      if (count == 1) {
        needle = true
      }
      count = 0
    }
    return needle
  }

  @main
  def test(): Unit = {
    // test1()
    // test2()
    // test3()
    test4()
  }

  def test2(): Unit = {
    // Input: grid = [[1,1,1],[1,0,1],[1,1,1]]

    println(
      isPossibleToCutPath(Array(Array(1, 1, 1), Array(1, 0, 1), Array(1, 1, 1)))
    )
  }
  def test1(): Unit = {
    // grid = [[1,1,1],[1,0,0],[1,1,1]]
    println(
      isPossibleToCutPath(Array(Array(1, 1, 1), Array(1, 0, 0), Array(1, 1, 1)))
    )
  }
  def test3(): Unit = {
    val grid = Array.fill(2000, 2000)(1)
    println(isPossibleToCutPath(grid))
  }
  def test4(): Unit = {
    // grid [[1,1,1],[1,0,0],[1,1,1],[1,1,1]]
    val grid =
      Array(Array(1, 1, 1), Array(1, 0, 0), Array(1, 1, 1), Array(1, 1, 1))
    println(isPossibleToCutPath(grid))
  }

}
