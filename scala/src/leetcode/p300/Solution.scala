package leetcode.p300

object xxx {
  @main
  def main() = {
    //val x = Solution.lengthOfLIS(Array(7, 7, 7, 7, 7, 7, 7))

    val x = Solution.lengthOfLIS(Array(1,3,6,7,9,4,10,5,6))

    //
    // val x =Solution.lengthOfLIS(Array(10,11,12,1,2,3,4,13))
    println(x)
  }
}

object Solution {
  implicit object PosOrdering extends Ordering[Pos] {
    def compare(x: Pos, y: Pos): Int = {
      if (x.value == y.value) {
        // return x.idx.compare(y.idx)
        return y.idx.compare(x.idx)
      } else {
        return x.value.compare(y.value)
        // y.value.compare(x.value)
      }
    }
  }
  def lengthOfLIS(nums: Array[Int]): Int = {
    var treeset = scala.collection.mutable.TreeSet.empty[Pos]
    var maxpath = 1
    for (i <- 0 until nums.size) {
      val ele = nums(i)
      val before = treeset.maxBefore(Pos(ele, i, 0))
      before match {
        case None => treeset.add(Pos(ele, i, 1))
        case Some(value) => {
          val newmax = value.seq + 1
          val mypos = Pos(ele, i, newmax)
          treeset.add(mypos)
          if (newmax > maxpath) {
            maxpath = newmax
          }
          val larger = treeset.rangeFrom(mypos)
          larger.foreach(p => if(p.seq < newmax) p.seq = newmax)
        }
      }
    }
    // treeset.foreach(x => println(s"${x.value} - ${x.idx}"))
    maxpath
  }

  case class Pos(val value: Int, val idx: Int, var seq: Int)
}
