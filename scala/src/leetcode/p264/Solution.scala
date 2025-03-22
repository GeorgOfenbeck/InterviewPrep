package leetcode.p264

object Solution {

  val cache = scala.collection.mutable.Map.empty[Int, Boolean]

  def nthUglyNumber(n: Int): Int = {

    var i = 0
    var num = 1

    while (i < n) {
      if (isUgly(num)) {
        i = i + 1
        // println(s"$num ${isUgly(num)}")
      }
      num = num + 1
    }
    num - 1

  }

  def isUgly(n: Int): Boolean = {
    def helper(n: Int): Boolean = {
      if (n == 0) return false
      if (n == 1) return true
      if (n % 2 == 0) return isUgly(n / 2)
      if (n % 3 == 0) return isUgly(n / 3)
      if (n % 5 == 0) return isUgly(n / 5)
      return false
    }
    if (cache.contains(n)) return cache(n)
    val res = helper(n)
    cache(n) = res
    return res
  }

  @main
  def test(): Unit = {

    println(nthUglyNumber(10))
  }
}
