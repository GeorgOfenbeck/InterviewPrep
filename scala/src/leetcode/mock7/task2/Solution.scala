package leetcode.mock7.task2

import scala.annotation.tailrec

object Solution {
  def numTilePossibilities(tiles: String): Int = {
    nrPossible(countMap(tiles), tiles.size) - 1 // all blanks
  }

  def countMap(tiles: String): Map[Char, Int] = {
    tiles.foldLeft(Map.empty[Char, Int])((acc, ele) => {
      acc.updatedWith(ele)(_ match {
        case None        => Some(1)
        case Some(value) => Some(value + 1)
      })
    })
  }

  def nrPossible(
      options: Map[Char, Int],
      depth: Int
  ): Int = {
    if (depth == 0) return 1
    val variants = options.keySet

    val count = variants.map { variant =>
      {
        val pick = options.updatedWith(variant)(_ match {
          case None        => assert(false)
          case Some(value) => if (value == 1) None else Some(value - 1)
        })
        nrPossible(pick, depth - 1)
      }
    }.sum

    count + nrPossible(options, depth - 1)
  }

  @main
  def test(): Unit = {
    val tiles = "AB"
    println(numTilePossibilities(tiles))
  }
}
