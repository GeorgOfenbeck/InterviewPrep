package leetcode.p315

object Solution {
  def countSmaller(nums: Array[Int]): List[Int] = {

    val tset = scala.collection.mutable.TreeSet[Int]()

    //val res: Array[Int] = new Array[Int](nums.size)
    //var idx = 0
    var res: List[Int] = List()
    for (n <- nums.reverseIterator){
      tset.add(n)
      val nit = tset.iteratorFrom(n) //bigger or equal then
      //res(idx) = nit.size
      res = (tset.size - nit.size) +: res
    }
    res

  }
}