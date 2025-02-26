package leetcode.mock22.task2

object Solution {
  def updateMatrix(mat: Array[Array[Int]]): Array[Array[Int]] = {
    val cacheLU = Array.fill(mat.size, mat(0).size)(-1)
    val cacheLD = Array.fill(mat.size, mat(0).size)(-1)
    val cacheRU = Array.fill(mat.size, mat(0).size)(-1)
    val cacheRD = Array.fill(mat.size, mat(0).size)(-1)

    val res = Array.fill(mat.size, mat(0).size)(-1)
    for (i <- 0 until mat.size) {
      for (j <- 0 until mat(0).size) {
        val min = Vector(
          nearest(i, j, mat, cacheLU, 1, -1),
          nearest(i, j, mat, cacheLD, -1, -1),
          nearest(i, j, mat, cacheRU, 1, 1),
          nearest(i, j, mat, cacheRD, -1, 1)
        ).min
        res(i)(j) = min
      }
    }
    res
  }

  def nearest(
      x: Int,
      y: Int,
      mat: Array[Array[Int]],
      cache: Array[Array[Int]],
      xd: Int,
      yd: Int
  ): Int = {
    if (x < 0 || x >= mat.size || y < 0 || y >= mat(0).size) return Int.MaxValue

    if (cache(x)(y) != -1) {
      return cache(x)(y)
    }

    if (mat(x)(y) == 0) {
      cache(x)(y) = 0
      0
    } else {
      val right = nearest(x, y + yd, mat, cache, xd, yd)
      val up = nearest(x + xd, y, mat, cache, xd, yd)

      val min = Vector(up, right).min
      val res = if (min == Int.MaxValue) Int.MaxValue else min + 1
      cache(x)(y) = res
      res
    }
  }

}
