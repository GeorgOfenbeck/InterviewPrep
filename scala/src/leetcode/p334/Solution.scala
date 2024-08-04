package leetcode.p334

object Solution {
  def increasingTriplet(nums: Array[Int]): Boolean = {
    var lowestfirst = Integer.MAX_VALUE
    var lowestsecond = Integer.MAX_VALUE

    var third = false
    var i = 0
    while (i < nums.size) {
      val x = nums(i)
      if (x < lowestfirst) {
        lowestfirst = x
      } else {
        if (x < lowestsecond && x > lowestfirst)
          lowestsecond = x
        else {
          if (x > lowestsecond) {
            third = true
            i = nums.size
          }
        }
      }
      i = i + 1
    }

    third
  }
}
