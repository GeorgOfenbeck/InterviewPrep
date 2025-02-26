package leetcode.p3412

import scala.util.control.Breaks._

object Solution {
  def calculateScore(s: String): Long = {
    s.iterator
      .map(_ - 'a')
      .map(ch => (ch, 25 - ch))
      .zipWithIndex
      .scanLeft((0, Map.empty[Int, List[Int]])) {
        case ((_, aMap), ((ch, mr), i)) =>
          if (aMap.contains(ch))
            (
              i - aMap(ch).head,
              aMap.updatedWith(ch) { _.map(_.tail).filter(_.nonEmpty) }
            )
          else (0, (aMap + ((mr, i +: aMap.getOrElse(mr, Nil)))))
      }
      .map(_._1.toLong)
      .sum
  }
  def calculateScore2(s: String): Long = {

    val mark = Array.fill[Boolean](s.size)(false)

    val abc = Array.ofDim[Char](26)
    val mirror = Array.ofDim[Char](26)

    abc(0) = 'a'
    for (i <- 1 until abc.size) {
      abc(i) = (abc(i - 1).toInt + 1).toChar
    }
    for (i <- 0 until abc.size) {
      mirror(i) = abc(abc.size - 1 - i)
    }

    var sum: Long = 0
    for (i <- 0 until s.size) {
      val iletter = s(i)
      val iidx = (iletter - 'a').toInt
      breakable {
        for (j <- (0 until (i - 1)).reverse) {
          val jletter = s(j)
          val isMirror = (mirror(iidx) == jletter)
          if (isMirror) {
            if (!mark(j)) {
              sum = sum + (i - j)
              mark(i) = true
              mark(j) = true
              break
            }
          }
        }
      }
    }

    sum
  }
}
