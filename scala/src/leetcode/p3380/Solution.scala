// To execute Scala code, please define an object named Solution that extends App

package leetcode.p3380

import scala.collection.{mutable => mu}
import scala.collection.immutable.TreeSet
import scala.collection.immutable.TreeSet.TreeSetBuilder

object Solution {

  case class Point(x: Int, y: Int)

  def maxRectangleArea(points: Array[Array[Int]]): Int = {
    val pvec = points.map(arr => Point(arr(0), arr(1))).toVector

    val colMap = mu.Map.empty[Int, TreeSet[Int]]
    val rowMap = mu.Map.empty[Int, TreeSet[Int]]

    val bhorizontal =
      TreeSet.newBuilder[Point](Ordering.by(point => (point.y, point.x)))
    val bvertical =
      TreeSet.newBuilder[Point](Ordering.by(point => (point.x, point.y)))

    for (vec <- pvec) {
      bhorizontal.addOne(vec)
      bvertical.addOne(vec)
      colMap.updateWith(vec.y)(opt =>
        opt match {
          case None => Some(TreeSet(vec.x))
          case Some(value) => {
            Some(value + vec.x)
          }
        }
      )
      rowMap.updateWith(vec.x)(opt =>
        opt match {
          case None => Some(TreeSet(vec.y))
          case Some(value) => {
            Some(value + vec.y)
          }
        }
      )
    }
    val vertical = bvertical.result()
    val horizontal = bhorizontal.result()

    var maxArea = -1
    for (vec <- pvec) {
      val col = colMap(vec.y)
      val row = rowMap(vec.x)

      val rowopt = col.minAfter(vec.x + 1)
      val colopt = row.minAfter(vec.y + 1)

      val otherrow = colopt.flatMap(y => colMap(y).minAfter(vec.x + 1))
      val othercol = rowopt.flatMap(x => rowMap(x).minAfter(vec.y + 1))

      (otherrow, othercol, colopt, rowopt) match {
        case (Some(orow), Some(ocol), Some(acol), Some(arow)) => {
          if (orow == arow && ocol == acol) {
            val area = (orow - vec.x) * (ocol - vec.y)
            if (area > maxArea) {
              val other = Point(orow, ocol)
              if (!hasInside(vec, other, vertical, horizontal)) {
                println(s"vec: $vec, Point: $other")
                maxArea = area
              }
            }
          }
        }
        case _ =>
      }
    }
    maxArea
  }

  def hasInside(
      topLeft: Point,
      bottomRight: Point,
      vertical: TreeSet[Point],
      horizontal: TreeSet[Point]
  ): Boolean = {
    val leftBottom = Point(bottomRight.x, topLeft.y)
    val rightTop = Point(topLeft.x, bottomRight.y)

    // val yordering: Ordering[Point] = Ordering.by(point => (point.y, point.x))
    // val xordering: Ordering[Point] = Ordering.by(point => (point.x, point.y))

    val inVertical = vertical.range(topLeft, bottomRight)
    val inHorizontal = horizontal.range(topLeft, bottomRight)
    val unionSet = inVertical.intersect(
      inHorizontal
    ) - topLeft - leftBottom - rightTop

    val area = (bottomRight.x - topLeft.x) * (bottomRight.y - topLeft.y)
    if (unionSet.isEmpty) false
    else true
  }

  @main
  def test(): Unit = {
    // [[1,1],[1,3],[3,1],[3,3]]
    // [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]
    println(
      maxRectangleArea(
        Array(
          Array(1, 1),
          Array(1, 3),
          Array(3, 1),
          Array(3, 3)
        )
      )
    )
  }
  @main
  def test2(): Unit = {
    // [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]
    println(
      maxRectangleArea(
        Array(
          Array(1, 1),
          Array(1, 3),
          Array(3, 1),
          Array(3, 3),
          Array(1, 2),
          Array(3, 2)
        )
      )
    )
  }

  @main
  def test3(): Unit = {
    // [[1,1],[1,3],[3,1],[3,3],[2,2]]
    println(
      maxRectangleArea(
        Array(
          Array(1, 1),
          Array(1, 3),
          Array(3, 1),
          Array(3, 3),
          Array(2, 2)
        )
      )
    )
  }

  @main
  def test4(): Unit = {
    // [[16,77],[76,92],[16,92],[76,77],[86,76],[54,98],[57,29],[54,91],[17,74]]
    println(
      maxRectangleArea(
        Array(
          Array(16, 77),
          Array(76, 92),
          Array(16, 92),
          Array(76, 77),
          Array(86, 76),
          Array(54, 98),
          Array(57, 29),
          Array(54, 91),
          Array(17, 74)
        )
      )
    )
  }
  @main
  def test5(): Unit = {
    // [[1,1],[1,3],[3,1],[3,3],[2,2]]

    println(
      maxRectangleArea(
        Array(
          Array(1, 1),
          Array(1, 3),
          Array(3, 1),
          Array(3, 3),
          Array(2, 2)
        )
      )
    )
  }
  @main
  def test6(): Unit = {
    // [[13,90],[13,29],[80,90],[81,47],[80,29]]

    println(
      maxRectangleArea(
        Array(
          Array(13, 90),
          Array(13, 29),
          Array(80, 90),
          Array(81, 47),
          Array(80, 29)
        )
      )
    )
  }
  @main
  def test7(): Unit = {

    val treeSetBuilder =
      TreeSet.newBuilder[Point](Ordering.by(point => (point.y, point.x)))

    treeSetBuilder.addAll(
      Vector(
        Point(1, 10),
        Point(2, 20),
        Point(3, 30),
        Point(2, 30),
        Point(2, 31)
      )
    )
    val tree = treeSetBuilder.result()
    val range = tree.range(Point(1, 10), Point(3, 30))
    for (e <- range) {
      println(e)
    }

  }

}
