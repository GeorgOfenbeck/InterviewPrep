package leetcore.mock9.task1

object Solution {
  def nextClosestTime(time: String): String = {
    val (hh, mm) = timeToInt(time)

    val digits = findDigits(time)
    val mmTo60 = findMMTo60(mm, digits)
    val mmFrom0 = findMMfrom0(mm, digits)
    val hhTo23 = findHHTo23(hh, digits)
    val hhFrom0 = findHHfrom0(hh, digits)

    mmTo60 match {
      case None => {
        hhTo23 match {
          case None => {
            output(hhFrom0.get, mmFrom0.get)
          }
          case Some(newhh) => {
            output(newhh, mmFrom0.get)
          }
        }
      }
      case Some(newmm) => {
        output(hh, newmm)
      }
    }
  }

  def output(hh: Int, mm: Int): String = {
    val hs = if (hh < 10) s"0$hh" else hh.toString
    val ms = if (mm < 10) s"0$mm" else mm.toString
    s"${hs}:${ms}"
  }

  import scala.util.control.Breaks._
  def findMMTo60(mm: Int, digits: Set[Int]): Option[Int] = {
    var result: Option[Int] = None
    breakable {
      for (i <- mm + 1 to 59) {
        val l = i / 10
        val r = i % 10
        if (digits.contains(l) && digits.contains(r)) {
          result = Some(i)
          break
        }
      }
    }
    result
  }

  def findMMfrom0(mm: Int, digits: Set[Int]): Option[Int] = {
    var result: Option[Int] = None
    breakable {
      for (i <- 0 to mm) {
        val l = i / 10
        val r = i % 10
        if (digits.contains(l) && digits.contains(r)) {
          result = Some(i)
          break
        }
      }
    }
    result
  }

  def findHHTo23(mm: Int, digits: Set[Int]): Option[Int] = {
    var result: Option[Int] = None
    breakable {
      for (i <- mm + 1 to 23) {
        val l = i / 10
        val r = i % 10
        if (digits.contains(l) && digits.contains(r)) {
          result = Some(i)
          break
        }
      }
    }
    result
  }

  def findHHfrom0(mm: Int, digits: Set[Int]): Option[Int] = {
    var result: Option[Int] = None
    breakable {
      for (i <- 0 to mm) {
        val l = i / 10
        val r = i % 10
        if (digits.contains(l) && digits.contains(r)) {
          result = Some(i)
          break
        }
      }
    }
    result
  }
  def timeToInt(time: String): (Int, Int) = {
    val split = time.split(":").map(_.toInt)
    (split(0), split(1))
  }
  def findDigits(time: String): Set[Int] = {
    Set(time(0).asDigit, time(1).asDigit, time(3).asDigit, time(4).asDigit)
  }
}
