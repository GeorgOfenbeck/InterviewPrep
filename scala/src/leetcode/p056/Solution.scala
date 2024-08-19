package leetcode.p056

import scala.collection.Searching.SearchResult
import scala.collection.Searching.InsertionPoint
import scala.collection.Searching.Found
import scala.annotation.tailrec

object Solution {
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {

    object IntervalOrdering extends Ordering[Array[Int]] {
      def compare(x: Array[Int], y: Array[Int]): Int = {
        val res = x(0).compare(y(0))
        if (res == 0) x(1).compare(y(1)) else res
      }
    }

    val sorted = intervals.sorted(IntervalOrdering)
    var cur = 0
    var res = List.empty[Array[Int]]
    while (cur < sorted.size) {

      val endint = extendInterval(cur, sorted)
      res = Array(sorted(cur)(0), sorted(endint)(1)) +: res

      val next =
        binsearch(0, sorted.size, sorted, sorted(endint)(1) + 1, None, true)
      cur = next match {
        case Found(foundIndex)              => foundIndex
        case InsertionPoint(insertionPoint) => insertionPoint
      }
    }
    return res.reverse.toArray
  }

  def extendInterval(startPointidx: Int, sorted: Array[Array[Int]]): Int = {
    val startA = sorted(startPointidx)(0)
    val endA = sorted(startPointidx)(1)

    val point = binsearch(startPointidx, sorted.size, sorted, endA, None, false)
    point match {
      case InsertionPoint(insertionPoint) =>
        if (insertionPoint - 1 == startPointidx) {
          return startPointidx
        } else {
          rec(startPointidx, sorted, insertionPoint - 1)
        }
      case Found(foundIndex) => {
        rec(startPointidx, sorted, foundIndex)
      }
    }
  }

  def rec(
      startPointidx: Int,
      sorted: Array[Array[Int]],
      foundIndex: Int
  ): Int = {
    var maxEndPointIdx = startPointidx
    for (i <- startPointidx to foundIndex) {
      val endX = sorted(i)(1)
      if (endX >= sorted(maxEndPointIdx)(1)) {
        maxEndPointIdx = i
      }
    }

    if (maxEndPointIdx == startPointidx) {
      return startPointidx
    } else {
      return extendInterval(maxEndPointIdx, sorted)
    }
  }
  def binsearch(
      l: Int,
      r: Int,
      array: Array[Array[Int]],
      target: Int,
      found: Option[Int],
      left: Boolean
  ): SearchResult = {
    val mid = l + (r - l - 1) / 2
    if (r <= l) {
      found match {
        case None        => InsertionPoint(l)
        case Some(value) => Found(value)
      }
    } else {
      val check = array(mid)(0)
      if (check == target) {
        if (left) {
          binsearch(l, mid, array, target, Some(mid), left)
        } else {
          binsearch(mid + 1, r, array, target, Some(mid), left)
        }
      } else {
        if (check > target) {
          binsearch(l, mid, array, target, None, left)
        } else {
          binsearch(mid + 1, r, array, target, None, left)
        }

      }
    }
  }

}

object test {
  @main
  def main() = {
    val x = Array(
      Array(5, 6),
      Array(4, 4),
      Array(2, 2),
      Array(3, 3),
      Array(5, 5)
    )
    val y = Solution.merge(x)
    y.map(z => z.map(println))
  }
}
