package leetcode.mock25.task2

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

// To execute Scala code, please define an object named Solution that extends App

object Solution {
  def maxKilledEnemies(grid: Array[Array[Char]]): Int = {

    val fromLeft: Array[Array[Int]] = sweepRight(grid)
    val fromRight: Array[Array[Int]] = sweepLeft(grid)
    val fromUp: Array[Array[Int]] = sweepDown(grid)
    val fromDown: Array[Array[Int]] = sweepUp(grid)
    var maxKills = -1
    for (i <- 0 until grid.size) {
      for (j <- 0 until grid(0).size) {
        val sum = Vector(
          fromDown(i)(j),
          fromUp(i)(j),
          fromRight(i)(j),
          fromLeft(i)(j)
        ).sum
        if (grid(i)(j) == '0') {
          if (sum > maxKills)
            maxKills = sum
        }
      }
    }
    maxKills
  }

  def sweepRight(grid: Array[Array[Char]]): Array[Array[Int]] = {
    val cache = Array.fill[Int](grid.size, grid(0).size)(0)
    for (i <- 0 until grid.size) {
      for (j <- 0 until grid(0).size) {
        val before = prev(cache, i, j, 0, -1)
        update(grid, cache, i, j, before)
      }
    }
    cache
  }
  def sweepLeft(grid: Array[Array[Char]]): Array[Array[Int]] = {
    val cache = Array.fill[Int](grid.size, grid(0).size)(0)
    for (i <- (0 until grid.size)) {
      for (j <- (0 until grid(0).size).reverse) {
        val before = prev(cache, i, j, 0, 1)
        update(grid, cache, i, j, before)
      }
    }
    cache
  }
  def sweepUp(grid: Array[Array[Char]]): Array[Array[Int]] = {
    val cache = Array.fill[Int](grid.size, grid(0).size)(0)
    for (j <- 0 until (grid(0).size)) {
      for (i <- 0 until grid.size) {
        val before = prev(cache, i, j, -1, 0)
        update(grid, cache, i, j, before)
      }
    }
    cache
  }
  def sweepDown(grid: Array[Array[Char]]): Array[Array[Int]] = {
    val cache = Array.fill[Int](grid.size, grid(0).size)(0)
    for (j <- 0 until (grid(0).size)) {
      for (i <- (0 until grid.size).reverse) {
        val before = prev(cache, i, j, 1, 0)
        update(grid, cache, i, j, before)
      }
    }
    cache
  }

  def update(
      grid: Array[Array[Char]],
      cache: Array[Array[Int]],
      i: Int,
      j: Int,
      before: Int
  ): Unit = {
    if (grid(i)(j) == '0') {
      cache(i)(j) = before
    }
    if (grid(i)(j) == 'W') {
      cache(i)(j) = 0
    }
    if (grid(i)(j) == 'E') {
      cache(i)(j) = before + 1
    }
  }

  def prev(grid: Array[Array[Int]], i: Int, j: Int, di: Int, dj: Int): Int = {
    val ni = i + di
    val nj = j + dj
    if (ni < 0 || ni >= grid.size || nj < 0 || nj >= grid(0).size) return 0
    else return grid(ni)(nj)
  }

  @main
  def test(): Unit = {
//[["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
    println(
      maxKilledEnemies(
        Array(
          Array('0', 'E', '0', '0'),
          Array('E', '0', 'W', 'E'),
          Array('0', 'E', '0', '0')
        )
      )
    )
  }

}
