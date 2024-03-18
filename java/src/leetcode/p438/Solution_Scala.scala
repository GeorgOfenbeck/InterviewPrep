package leetcode.p438

object Solution_Scala extends App{

  val s = "cbaebabacd"
  val p = "abc"

  println(findAnagrams(s,p))
  println("blub")


  object CountingSet {
    def apply(s: String): CountingSet = {
      val map: Map[Char, Int] = s.foldLeft(Map.empty[Char, Int])((acc, ele) => {
        acc + (ele -> (acc.applyOrElse[Char, Int](ele, ele => 0) + 1))
      })
      new CountingSet(map)
    }
  }

  case class CountingSet(map: Map[Char, Int]) {

    val checkset = map.foldLeft(Set.empty[Char])((acc, ele) => {
      val (k, v) = ele
      if (v >= 1)
        acc + k
      else
        acc
    })

    def diff(that: CountingSet): CountingSet = {
      val ret = map.foldLeft(Map.empty[Char, Int])((acc, kv) => {
        val (ele, value) = kv
        val we = map.applyOrElse[Char, Int](ele, ele => 0)
        val they = that.map.applyOrElse[Char, Int](ele, ele => 0)
        acc + (ele -> Math.max(we - they, 0))
      })
      CountingSet(ret)
    }

    def add(c: Char): CountingSet = {
      val sofar: Int = map.getOrElse[Int](c, 0) + 1
      val nmap: Map[Char, Int] = map + (c -> sofar)
      CountingSet(nmap)
    }

    def addConditonal(c: Char, ref: CountingSet): CountingSet = {
      if (ref.map.contains(c)) {
        val sofar: Int = map.getOrElse[Int](c, 0) + 1
        val nmap: Map[Char, Int] = map + (c -> sofar)
        CountingSet(nmap)
      } else
        this
    }

    def remove(c: Char, ref: CountingSet): CountingSet = {
      if (ref.map.contains(c)) {
        val x: Int = map.getOrElse[Int](c, 0)
        CountingSet(map + (c -> (x - 1)))
      }
      else
        this
    }

  }


  case class State(diff: CountingSet, workingSet: List[Char], endIndicies: List[Int])

  def findAnagrams(s: String, p: String): List[Int] = {

    val anagram = CountingSet(p)
    val zippy = s.toCharArray.toVector.zipWithIndex
    val diff = CountingSet(p)

    zippy.foldLeft(State(diff, List.empty, List.empty))((acc, ele) => {
      val (c, idx) = ele
      val removefront = acc.diff.remove(c, anagram)
      if (idx == 0) {
        State(removefront, acc.workingSet :+ c, acc.endIndicies)
      }
      else {
        val addtail = if (idx >= p.size) removefront.addConditonal(acc.workingSet.head, anagram) else removefront
        val newws = acc.workingSet.tail :+ c
        println(s"idx: $idx $addtail ${addtail.checkset} $newws")

        if (addtail.checkset.isEmpty)
          State(addtail, newws, acc.endIndicies :+ (idx - p.size + 1))
        else
          State(addtail, newws, acc.endIndicies)
      }
    }).endIndicies
  }
}