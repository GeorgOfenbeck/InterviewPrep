package leetcode.mock22.task1

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._
object Solution {
  def wordPattern(pattern: String, s: String): Boolean = {

    val mmap = mu.Map.empty[Char, String]
    val bmap = mu.Map.empty[String, Char]
    val chars = pattern.toVector
    val words = s.split(" ").toVector

    if (chars.size != words.size) {
      // println(s"diff size ${chars} ${words}")
      return false
    }

    var corr = true
    breakable {
      for (i <- 0 until words.size) {
        val curc = chars(i)
        if (mmap.contains(curc)) {
          if (mmap(curc) != words(i)) {
            corr = false
            break
          }
        } else {
          if (bmap.contains(words(i))) {
            corr = false
            break
          }
          bmap.addOne(words(i), curc)
          mmap.addOne(curc, words(i))
        }
      }
    }
    return corr
  }

  @main
  def test(): Unit = {
    // pattern = "abba", s = "dog cat cat dog"
    println(wordPattern("abba", "dog cat cat dog")) // true
  }

  def wordPattern2(pattern: String, s: String): Boolean = {

    val xx = pattern.toVector.groupBy(x => identity(x))

    val x = Vector(1, 2, 3, 4, 4)
    val y = x.groupBy(identity)

    val cfreq = pattern.foldLeft(Map.empty[Char, Int])((acc, ele) => {
      acc.updatedWith(ele)(opt =>
        opt match {
          case None        => Some(1)
          case Some(value) => Some(value + 1)
        }
      )
    })

    val wfreq = s
      .split(" ")
      .foldLeft(Map.empty[String, Int])((acc, ele) => {
        acc.updatedWith(ele)(opt =>
          opt match {
            case None        => Some(1)
            case Some(value) => Some(value + 1)
          }
        )
      })

    val cfreqcount = cfreq.foldLeft(Map.empty[Int, Int])((acc, ele) => {
      val freq = ele._2
      acc.updatedWith(freq)(opt =>
        opt match {
          case None        => Some(1)
          case Some(value) => Some(value + 1)
        }
      )
    })
    val wfreqcount = wfreq.foldLeft(Map.empty[Int, Int])((acc, ele) => {
      val freq = ele._2
      acc.updatedWith(freq)(opt =>
        opt match {
          case None        => Some(1)
          case Some(value) => Some(value + 1)
        }
      )
    })

    wfreqcount == cfreqcount
  }
}
