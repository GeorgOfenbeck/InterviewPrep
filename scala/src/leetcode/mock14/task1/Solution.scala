package leetcode.mock14.task1

class CustomFunction {
  def f(x: Int, y: Int): Int = x + y
}

/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     def f(x: Int, y: Int): Int = {}
 * };
 */

import scala.collection.{mutable => mu}

object Solution {
  def findSolution(customfunction: CustomFunction, z: Int): List[List[Int]] = {

    // val x = 1 - 1000, y = 1 - 1000
    val res = mu.Set.empty[(Int, Int)]
    val upper = 1000
    rec(customfunction, z, 1, upper, 1, upper, res)
    res.toList.map { case (x, y) => List(x, y) }
  }

  def rec(
      customfunction: CustomFunction,
      z: Int,
      xl: Int,
      xh: Int,
      yl: Int,
      yh: Int,
      res: mu.Set[(Int, Int)]
  ): Unit = {

    if (xl > xh || yl > yh) return

    val midx = xl + (xh - xl) / 2
    val midy = yl + (yh - yl) / 2

    val midf = customfunction.f(midx, midy)

    // println(
    //   s"xl: $xl, xh: $xh, yl: $yl, yh: $yh, midx: $midx, midy: $midy, midf: $midf"
    // )
    if (z == midf) {
      res.add((midx, midy))
      rec(customfunction, z, xl, midx - 1, midy + 1, yh, res) // top left
      rec(customfunction, z, midx + 1, xh, yl, midy - 1, res) // bottom right
    } else {
      if (z > midf) {
        rec(customfunction, z, midx + 1, xh, midy + 1, yh, res) // top right
        rec(customfunction, z, xl, midx, midy + 1, yh, res) // top left
        rec(customfunction, z, midx + 1, xh, yl, midy, res) // bottom right
      } else {
        rec(customfunction, z, xl, midx - 1, yl, midy - 1, res) // bottom left
        rec(customfunction, z, xl, midx - 1, midy, yh, res) // top left
        rec(customfunction, z, midx, xh, yl, midy - 1, res) // bottom right
      }
    }
  }
  @main
  def test(): Unit = {
    val customfunction = new CustomFunction()
    val z = 5
    val res = findSolution(customfunction, z)
    println(res)
  }
}
