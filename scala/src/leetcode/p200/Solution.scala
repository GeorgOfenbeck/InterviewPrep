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
    getNumIslands(posSet.toSet, grid)
  }

  def getNumIslands(posSetIn: Set[Pos], grid: Array[Array[Char]]): Int = {
    var posSet = posSetIn
    var nrIslands = 0

    while (!posSet.isEmpty) {
      if (posSet.head.land) {
        nrIslands = nrIslands + 1
        posSet = explore(posSet, posSet.head, grid)
      }
      posSet = posSet - posSet.head
    }
    nrIslands
  }

  def explore(
      remain: Set[Pos],
      root: Pos,
      grid: Array[Array[Char]]
  ): Set[Pos] = {
    val withoutroot = remain - root
    if (root.land != true)
      return remain
    val x = root.x
    val y = root.y
    val up = Pos(x - 1, y, x > 0 && grid(x - 1)(y) == '1')
    val right = Pos(x, y + 1, y < grid(x).size - 1 && grid(x)(y + 1) == '1')
    val left = Pos(x, y - 1, y > 0 && grid(x)(y - 1) == '1')
    val down = Pos(x + 1, y, x < grid.size - 1 && grid(x + 1)(y) == '1')
    explore(
      explore(explore(explore(withoutroot, up, grid), right, grid), left, grid),
      down,
      grid
    )
  }


  @main
  def main() = {
    val grid = Array(
      Array('1', '1', '1', '1', '0'),
      Array('1', '1', '0', '1', '0'),
      Array('1', '1', '0', '0', '0'),
      Array('0', '0', '0', '0', '0')
    )
    numIslands(grid)
  }
}
