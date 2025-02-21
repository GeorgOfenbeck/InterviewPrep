package leetcode.p329

import scala.collection.{mutable => mu}

object Solution {
  def longestIncreasingPath(matrix: Array[Array[Int]]): Int = {
    val cache = Array.fill(matrix.size, matrix(0).size)(-1)
    var max = Int.MinValue
    for (i <- 0 until matrix.size) {
      for (j <- 0 until matrix(0).size) {
        val res = recurse(i, j, cache, matrix)
        if (res > max)
          max = res
      }
    }
    max
  }

  @inline
  def getVal(
      i: Int,
      j: Int,
      matrix: Array[Array[Int]]
  ): Int = {
    if (i < 0 || i > matrix.size - 1 || j < 0 || j > matrix(0).size - 1) {
      return Int.MaxValue
    } else
      return matrix(i)(j)
  }
  def recurse(
      i: Int,
      j: Int,
      cache: Array[Array[Int]],
      matrix: Array[Array[Int]]
  ): Int = {
    if (i < 0 || i > matrix.size - 1 || j < 0 || j > matrix(0).size - 1) {
      return -1
    }
    val cached = cache(i)(j)

    val X = matrix(i)(j)

    if (cached != -1) return cached

    val fromU =
      if (X > getVal(i - 1, j, matrix)) recurse(i - 1, j, cache, matrix) + 1
      else 1

    val fromD =
      if (X > getVal(i + 1, j, matrix)) recurse(i + 1, j, cache, matrix) + 1
      else 1
    val fromL =
      if (X > getVal(i, j - 1, matrix)) recurse(i, j - 1, cache, matrix) + 1
      else 1
    val fromR =
      if (X > getVal(i, j + 1, matrix)) recurse(i, j + 1, cache, matrix) + 1
      else 1

    val fromX = Vector(fromU, fromD, fromR, fromL).max

    cache(i)(j) = fromX
    fromX
  }

  @main
  def test(): Unit = {
    // matrix = [[3,4,5],[3,2,6],[2,2,1]]
    println(
      longestIncreasingPath(
        Array(Array(3, 4, 5), Array(3, 2, 6), Array(2, 2, 1))
      )
    )
  }
}
