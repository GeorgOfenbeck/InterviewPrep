package leetcode.p063

object Solution {
  def uniquePathsWithObstacles(obstacleGrid: Array[Array[Int]]): Int = {

    val above = Array.fill(obstacleGrid(0).length)(0)
    var left = 1

    for (i <- 0 until obstacleGrid.size) {
      for (j <- 0 until obstacleGrid(i).size) {
        if (obstacleGrid(i)(j) == 1) {
          above(j) = 0
        } else {
          above(j) = above(j) + left
        }
        left = above(j)
      }
      left = 0
    }
    above.last
  }

  @main
  def test(): Unit = {
    println(
      uniquePathsWithObstacles(
        Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))
      )
    )
    println(uniquePathsWithObstacles(Array(Array(0, 1), Array(0, 0))))
  }
}
