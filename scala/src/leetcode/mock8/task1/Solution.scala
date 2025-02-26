package leetcode.mock8.task1

import scala.collection.{mutable => mu}

object Solution {
  def numTilePossibilities(tiles: String): Int = {
    rec(countMap(tiles))
    // results.size
  }

  def countMap(tiles: String): Map[Char, Int] = {
    tiles.foldLeft(Map.empty[Char, Int])((acc, ele) => {
      acc.updatedWith(ele)(_ match {
        case None        => Some(1)
        case Some(value) => Some(value + 1)
      })
    })
  }

  def rec(
      countMap: Map[Char, Int]
  ): Int = {

    countMap
      .map((k, v) => {

        val pick = countMap.updatedWith(k)(_ match {
          case None        => assert(false)
          case Some(value) => if (value == 1) None else Some(value - 1)
        })
        1 + rec(pick)
      })
      .sum
  }

}
