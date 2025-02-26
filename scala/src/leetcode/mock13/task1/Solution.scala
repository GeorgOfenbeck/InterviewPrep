package leetcode.mock13.task1

import scala.collection.{mutable => mu}

object Solution {
  def largestTimeFromDigits(arr: Array[Int]): String = {

    val digits = arr.foldLeft(Map.empty[Int, Int])((acc, el) => {
      acc + (el -> (acc.getOrElse(el, 0) + 1))
    })

    setH(digits) match {
      case Some(value) => value
      case None        => ""
    }
  }

  def decOrDelete(digits: Map[Int, Int], k: Int): Map[Int, Int] = {
    if (digits.contains(k)) {
      digits.updatedWith(k)(opt =>
        opt match {
          case Some(value) => if (value == 1) None else Some(value - 1)
          case None        => None
        }
      )
    } else Map.empty
  }

  def setH(digits: Map[Int, Int]): Option[String] = {
    val twenty = if (digits.contains(2)) {
      seth(decOrDelete(digits, 2), true)
    } else None
    twenty match {
      case Some(value) => Some(s"2$value")
      case None => {
        var i = 1
        var res: Option[String] = None
        while (i >= 0 && res.isEmpty) {
          res = seth(decOrDelete(digits, i), false) match {
            case Some(value) =>
              if (i == 0) Some(s"0$value") else Some(s"$i$value")
            case None => None
          }
          i = i - 1
        }

        res
      }
    }
  }

  def seth(digits: Map[Int, Int], twenty: Boolean): Option[String] = {
    if (digits.isEmpty) return None
    var i = if (twenty == true) {
      3
    } else 9
    var res: Option[String] = None
    while (i >= 0 && res.isEmpty) {
      res = setM(decOrDelete(digits, i)) match {
        case Some(value) =>
          if (i == 0) Some(s"0:$value") else Some(s"$i:$value")
        case None => None
      }
      i = i - 1
    }
    res
  }

  def setM(digits: Map[Int, Int]): Option[String] = {

    if (digits.isEmpty) return None
    var i = 5
    var res: Option[String] = None
    while (i >= 0 && res.isEmpty) {
      res = setm(decOrDelete(digits, i)) match {
        case Some(value) => if (i == 0) Some(s"0$value") else Some(s"$i$value")
        case None        => None
      }
      i = i - 1
    }
    res
  }
  def setm(digits: Map[Int, Int]): Option[String] = {
    if (digits.isEmpty) return None
    var i = 9
    var res: Option[String] = None
    while (i >= 0 && res.isEmpty) {
      if (digits.contains(i)) {
        res = Some(i.toString)
      }
      i = i - 1
    }
    res
  }

  @main
  def test(): Unit = {
    // arr = [1,2,3,4]
    println(largestTimeFromDigits(Array(1, 2, 3, 4))) // "23:41"
  }
}
