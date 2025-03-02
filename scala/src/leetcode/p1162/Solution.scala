package leetcode.p1162

import scala.util.control.Breaks._

object Solution {
  def maxDistance(grid: Array[Array[Int]]): Int = {
    if (!check(grid)) return -1

    val r1 = Array.fill(grid.length, grid(0).length)(-1)
    val r2 = Array.fill(grid.length, grid(0).length)(-1)
    val r3 = Array.fill(grid.length, grid(0).length)(-1)
    val r4 = Array.fill(grid.length, grid(0).length)(-1)

    sweepBottomLeft(grid, r1)
    sweepBottomRight(grid, r2)
    sweepTopLeft(grid, r3)
    sweepTopRight(grid, r4)

    var maxDistance = -1
    for (i <- 0 until grid.length) {
      for (j <- 0 until grid(0).length) {
        val dist = Vector(r1(i)(j), r2(i)(j), r3(i)(j), r4(i)(j)).min
        if (dist > maxDistance) {
          maxDistance = dist
        }
      }
    }
    // println(r1.map(_.mkString(" ")).mkString("\n"))
    // println
    // println(r2.map(_.mkString(" ")).mkString("\n"))
    // println
    // println(r3.map(_.mkString(" ")).mkString("\n"))
    // println
    // println(r4.map(_.mkString(" ")).mkString("\n"))
    maxDistance

  }

  def check(grid: Array[Array[Int]]): Boolean = {
    var nrOfOnes = 0
    var nrOfZeros = 0
    breakable {
      for (i <- 0 until grid.length) {
        for (j <- 0 until grid(0).length) {
          if (grid(i)(j) == 1) {
            nrOfOnes += 1
          } else {
            nrOfZeros += 1
          }
          if (nrOfOnes > 0 && nrOfZeros > 0) {
            break
          }
        }
      }
    }
    nrOfOnes > 0 && nrOfZeros > 0
  }

  def get(
      grid: Array[Array[Int]],
      cache: Array[Array[Int]],
      i: Int,
      j: Int
  ): Int = {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid(0).length) {
      return Int.MaxValue
    }
    if (cache(i)(j) != -1) {
      return cache(i)(j)
    }
    cache(i)(j) = grid(i)(j)
    return cache(i)(j)
  }

  def update(
      grid: Array[Array[Int]],
      cache: Array[Array[Int]],
      a: Int,
      b: Int,
      i: Int,
      j: Int
  ): Unit = {
    if (grid(i)(j) == 0) {
      if (a == Int.MaxValue && b == Int.MaxValue) {
        cache(i)(j) = Int.MaxValue
      } else {
        if (a == Int.MaxValue) {
          cache(i)(j) = b + 1
        } else if (b == Int.MaxValue) {
          cache(i)(j) = a + 1
        } else {
          cache(i)(j) = Math.min(a, b) + 1
        }
      }
    } else {
      cache(i)(j) = 0
    }
  }

  def sweepTopLeft(grid: Array[Array[Int]], cache: Array[Array[Int]]): Unit = {
    for (i <- 0 until grid.length) {
      for (j <- 0 until grid(0).length) {
        update(
          grid,
          cache,
          get(grid, cache, i - 1, j),
          get(grid, cache, i, j - 1),
          i,
          j
        )
      }
    }
  }
  def sweepTopRight(grid: Array[Array[Int]], cache: Array[Array[Int]]): Unit = {
    for (i <- 0 until grid.length) {
      for (j <- (0 until grid(0).length).reverse) {
        update(
          grid,
          cache,
          get(grid, cache, i - 1, j),
          get(grid, cache, i, j + 1),
          i,
          j
        )
      }
    }
  }
  def sweepBottomLeft(
      grid: Array[Array[Int]],
      cache: Array[Array[Int]]
  ): Unit = {
    for (i <- (0 until grid.length).reverse) {
      for (j <- 0 until grid(0).length) {
        update(
          grid,
          cache,
          get(grid, cache, i + 1, j),
          get(grid, cache, i, j - 1),
          i,
          j
        )
      }
    }
  }
  def sweepBottomRight(
      grid: Array[Array[Int]],
      cache: Array[Array[Int]]
  ): Unit = {
    for (i <- (0 until grid.length).reverse) {
      for (j <- (0 until grid(0).length).reverse) {
        update(
          grid,
          cache,
          get(grid, cache, i + 1, j),
          get(grid, cache, i, j + 1),
          i,
          j
        )
      }
    }
  }
  @main
  def test(): Unit = {
    val grid = Array(
      Array(1, 0, 1),
      Array(0, 0, 0),
      Array(1, 0, 1)
    )
    println(maxDistance(grid))
  }
  @main
  def test2(): Unit = {
    val grid = Array(
      Array(1, 0, 0),
      Array(0, 0, 0),
      Array(0, 0, 0)
    )
    println(maxDistance(grid))
  }
}
