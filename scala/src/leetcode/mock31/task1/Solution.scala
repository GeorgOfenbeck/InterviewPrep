package leetcode.mock31.task1

import scala.util.control.Breaks._

object Solution {
  def buddyStrings(s: String, goal: String): Boolean = {

    if (s.size != goal.size) return false

    var res = true
    var swapped = false
    var first: Option[(Char, Char)] = None

    var map = scala.collection.mutable.Map.empty[Char, Int]
    breakable {
      for (i <- 0 until s.size) {
        map += s(i) -> (map.getOrElse(s(i), 0) + 1)
        if (s(i) != goal(i)) {
          first match {
            case None => {
              first = Some((s(i), goal(i)))
            }
            case Some((sv, gv)) => {
              if (swapped) {
                res = false
                break
              }
              if (sv == goal(i) && gv == s(i)) {
                // we can swap the two
                swapped = true
              } else {
                res = false
                break
              }
            }
          }
        }
      }
    }
    if (!swapped && first.isEmpty && res == true) {
      map.dropWhile((k, v) => v < 2).size > 0
    } else {
      if (!swapped && first.isDefined) {
        false
      } else {
        res
      }
    }
  }
}
