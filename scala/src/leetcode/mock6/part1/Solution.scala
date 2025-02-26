package leetcode.mock6.part1

object Solution {

  def uncommonFromSentences(s1: String, s2: String): Array[String] = {

    val wordsa = s1.split(" ")
    val wordsb = s2.split(" ")

    val fmapa = wordsa.foldLeft(Map.empty[String, Int]) { (acc, ele) =>
      {
        acc.updatedWith(ele)(opt =>
          opt match {
            case None        => Some(1)
            case Some(value) => Some(value + 1)
          }
        )
      }
    }
    val fmapb = wordsb.foldLeft(Map.empty[String, Int]) { (acc, ele) =>
      {
        acc.updatedWith(ele)(opt =>
          opt match {
            case None        => Some(1)
            case Some(value) => Some(value + 1)
          }
        )
      }
    }

    val uncommonA = fmapa.filter(p => p._2 == 1).keySet
    val uncommonB = fmapb.filter(p => p._2 == 1).keySet

    val ares = uncommonA.filter(x => !fmapb.keySet.contains(x))
    val bres = uncommonB.filter(x => !fmapa.keySet.contains(x))

    (ares ++ bres).toArray

  }

}
