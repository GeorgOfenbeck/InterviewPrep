package leetcode.mock11.task2

import scala.collection.{mutable => mu}

object Solution {

  val print = false
  case class Point(x: Int, y: Int)

  def numSubmat(mat: Array[Array[Int]]): Int = {
    val cache = mu.Map.empty[(Point, Point), Boolean].withDefaultValue(false)

    for (i <- 0 until mat.size) {
      for (j <- 0 until mat(0).size) {
        cache((Point(i, j), Point(i, j))) = mat(i)(j) == 1
      }
    }

    for (i <- 0 until mat.size) {
      for (j <- 0 until mat(0).size) {
        growDiag(Point(i, j), Point(i, j), mat, cache)
      }
    }

    cache.filter((k, v) => v).size
  }

  def inBounds(p: Point, mat: Array[Array[Int]]): Boolean = {
    p.x >= 0 && p.x < mat.length && p.y >= 0 && p.y < mat(0).length
  }

  def growDiag(
      a: Point,
      b: Point,
      mat: Array[Array[Int]],
      cache: mu.Map[(Point, Point), Boolean]
  ): Unit = {
    if (inBounds(a, mat) && inBounds(b, mat)) {
      if (print)
        println(s"Growing diag from $a to $b")

      val above = if (a.x == b.x) a else b.copy(x = b.x - 1)
      val left = if (a.y == b.y) a else b.copy(y = b.y - 1)
      val X = mat(b.x)(b.y)

      val canGrow = X == 1 && cache((a, above)) && cache((a, left))

      if (canGrow) {
        cache((a, b)) = true

        growDown(a, b.copy(x = b.x + 1), mat, cache)
        growRight(b, b.copy(y = b.y + 1), mat, cache)
        growDiag(a, Point(b.x + 1, b.y + 1), mat, cache)
      } else cache((a, b)) = false

    }

  }

  def growDown(
      a: Point,
      b: Point,
      mat: Array[Array[Int]],
      cache: mu.Map[(Point, Point), Boolean]
  ): Unit = {
    if (inBounds(a, mat) && inBounds(b, mat)) {

      if (print)
        println(s"Growing down from $a to $b")
      val above = if (a.x == b.x) a else b.copy(x = b.x - 1)
      val left = if (a.y == b.y) a else b.copy(y = b.y - 1)

      val X = mat(b.x)(b.y)
      val canGrow = X == 1 && cache((a, above)) && cache((a, left))
      if (canGrow) {
        cache((a, b)) = true
        val below = b.copy(x = b.x + 1)
        growDown(a, below, mat, cache)
      } else cache((a, b)) = false

    }
  }

  def growRight(
      a: Point,
      b: Point,
      mat: Array[Array[Int]],
      cache: mu.Map[(Point, Point), Boolean]
  ): Unit = {
    if (inBounds(a, mat) && inBounds(b, mat)) {

      if (print)
        println(s"Growing right from $a to $b")
      val above = if (a.x == b.x) a else b.copy(x = b.x - 1)
      val left = if (a.y == b.y) a else b.copy(y = b.y - 1)

      val X = mat(b.x)(b.y)
      val canGrow = X == 1 && cache((a, above)) && cache((a, left))
      if (canGrow) {
        cache((a, b)) = true
        val right = b.copy(y = b.y + 1)
        growRight(a, right, mat, cache)
      } else cache((a, b)) = false
    }
  }

  @main
  def test(): Unit = {
    // [[0,1,1,1],[1,1,0,1],[1,1,0,0],[1,1,1,1],[0,1,0,0]/
    val mat = Array(
      Array(1, 0, 1),
      Array(1, 1, 0),
      Array(1, 1, 0)
    )
    println(numSubmat(mat))
  }
  @main
  def test2(): Unit = {
    // [[0,1,1,1],[1,1,0,1],[1,1,0,0],[1,1,1,1],[0,1,0,0]/
    val mat = Array(
      Array(0, 1, 1, 1),
      Array(1, 1, 0, 1),
      Array(1, 1, 0, 0),
      Array(1, 1, 1, 1),
      Array(0, 1, 0, 0)
    )
    println(numSubmat(mat))
  }
}
