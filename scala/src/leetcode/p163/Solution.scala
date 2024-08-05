package leetcode.p163

object Solution {

  @main
  def main() ={
    findMissingRanges(Array(0,1,3,50,75),0,99)
  }

  def findMissingRanges(
      nums: Array[Int],
      lower: Int,
      upper: Int
  ): List[List[Int]] = {

    var res = List.empty[List[Int]]

    if (nums.isEmpty) {
      return List(List(lower, upper))
    } else {
      var prev = upper

      var i = nums.size - 1

      while (i >= 0) {
        val cur = nums(i)
        if (cur != prev) {
          res = List(cur+1, prev) +: res
        }
        prev = cur - 1
        i = i - 1
      }

      // lower bound
      if (lower != nums(0))
        res = List(lower, nums(0)-1) +: res

      res
    }
  }
}
