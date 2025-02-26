package leetcode.mock2.task2

object Solution {
  def balancedStringSplit(s: String): Int = {

    var nrbalanced = 0

    var balance = 0
    for (i <- 0 until s.size) {
      if (s(i) == 'R') {
        balance = balance + 1
      } else {
        balance = balance - 1
      }
      if (balance == 0) {
        nrbalanced = nrbalanced + 1
      }
    }
    nrbalanced
  }
}
