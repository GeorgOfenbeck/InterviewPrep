package leetcode.mock2.task1

import scala.collection.{mutable => mu}
object Solution {

  def commonChars(words: Array[String]): List[String] = {
    val sets = words.map(word => wordToMap(word))
    val setmap: Map[Char, Int] = sets.reduce((a, b) => merge(a, b))
    mapToString(setmap)
  }

  def mapToString(a: Map[Char, Int]): List[String] = {
    var res: List[String] = List()
    for (ak <- a.keySet) {
      for (i <- 0 until a(ak)) {
        ak.toString() +: res
      }
    }
    res
  }

  def merge(a: Map[Char, Int], b: Map[Char, Int]): Map[Char, Int] = {
    val lmap = mu.Map.empty[Char, Int]
    for (ak <- a.keySet) {
      if (b.contains(ak)) {
        lmap.update(ak, Math.min(a(ak), b(ak)))
      }
    }
    lmap.toMap
  }

  def wordToMap(word: String): Map[Char, Int] = {
    val lmap = mu.Map.empty[Char, Int]
    for (c <- word) {
      lmap.updateWith(c)(opt =>
        opt match {
          case Some(v) => Some(v + 1)
          case None    => Some(1)
        }
      )
    }
    lmap.toMap
  }

}
