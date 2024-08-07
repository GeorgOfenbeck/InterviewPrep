package leetcode.p200

object Solution {

  case class Pos(val x: Int, val y: Int, val land: Boolean)

  def numIslands(grid: Array[Array[Char]]): Int = {
    var max = 0

    val posSet = scala.collection.mutable.Set.empty[Pos]
    for (i <- 0 until grid.size) {
      for (j <- 0 until grid(i).size) {
        posSet.add(Pos(i, j, grid(i)(j) == '1'))
      }
    }

    max
  }

  def getNumIslands(posSet: Set[Pos]): Int = {
    
  }

  def explore(remain: Set[Pos], root: Pos) = {
    
  }

}
