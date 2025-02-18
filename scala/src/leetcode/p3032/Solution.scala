package leetcode.p3032

//TODO
object Solution {
  def numberCount(a: Int, b: Int): Int = {
    return 1
  }

  def single(digitsTaken: Int): Int = {
    10 - digitsTaken
  }

  def rec(digitsTaken: Int, pos: Int): Int = {
    if (pos == 0) {
      10 - digitsTaken
    } else {
      10 * rec(digitsTaken + 1, pos - 1)
    }
  }

  def slow(n: Int): Int = {
    var count = 0
    for (i <- 0 to n) {
      val num = i.toString
      if (num.size == num.toSet.size)
        count = count + 1
    }
    count
  }

  @main
  def test(): Unit = {
    // println(slow(120) - slow(80 - 1))
    println(slow(99))
    println(rec(0, 1))
    println()
  }
}
