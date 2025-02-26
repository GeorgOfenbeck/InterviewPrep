package leetcode.mock12.task1

import scala.collection.{mutable => mu}

object Solution {
  def checkRecord(s: String): Boolean = {

    var absent = 0
    var late = 0
    var maxLate = 0

    var i = 0

    while (i < s.size && !(maxLate > 2 || absent > 1)) {

      if (s(i) == 'A') {
        absent = absent + 1
      }
      if (s(i) == 'L') {
        if (i == 0) {
          late = 1
        } else {
          if (s(i - 1) == 'L') {
            late = late + 1
            if (late > maxLate) {
              maxLate = late
            }
          } else {
            late = 1
          }
        }
      }
      i = i + 1
    }

    // max 3 days late - max 1 day absent
    if (maxLate > 2 || absent > 1) {
      return false
    } else return true

  }
}
