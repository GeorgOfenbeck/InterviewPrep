package leetcode.algorithms.BinSearch

object Solution {

  def binSearch(a: Array[Int], k: Int, l: Int, u: Int): Int = {
    println(s"l: $l, u: $u")
    if (l > u) return -1
    val mid = l + (u - l) / 2
    val midVal = a(mid)
    if (midVal == k) {
      return mid
    } else {
      if (midVal > k) {
        binSearch(a, k, l, mid - 1)
      } else {
        binSearch(a, k, mid + 1, u)
      }
    }
  }

  @main
  def test(): Unit = {
    val a = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    val k = 11
    val l = 0
    val u = a.length - 1
    val res = binSearch(a, k, l, u)
    println(res)
  }

}
