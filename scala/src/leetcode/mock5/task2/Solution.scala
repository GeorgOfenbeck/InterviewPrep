package leetcode.mock5.task2

import scala.collection.{mutable => mu}
import scala.collection.immutable.TreeSet

object Solution {
  def combinationSum(candidates: Array[Int], target: Int): List[List[Int]] = {
    val res = findCombo(
      TreeSet.from(candidates),
      target,
      Vector.empty,
      mu.Map.empty[Int, Set[Vector[Int]]]
    )
    res.map(v => v.toList).toList
  }

  def findCombo(
      options: TreeSet[Int],
      target: Int,
      sofar: Vector[Int],
      cache: mu.Map[Int, Set[Vector[Int]]]
  ): Set[Vector[Int]] = {
    cache.get(target) match {
      case None => {
        var tocache = Set.empty[Vector[Int]]
        val candidates = options.rangeTo(target + 1)
        for (candidate <- candidates) {
          val diff = target - candidate
          if (diff == 0) {
            val res: Vector[Int] = Vector(candidate)
            tocache = tocache + res
          }
          if (diff < 0) {
            // skip
          } else {
            // diff >0
            // how can i create "diff" combo
            val res: Set[Vector[Int]] =
              findCombo(options, diff, Vector.empty, cache)
            val combined = res.map(v => (v :+ candidate).sorted)
            tocache = tocache ++ combined
          }
        }
        cache.addOne(target -> tocache)
        tocache
      }
      case Some(value) => value
    }

  }

}
