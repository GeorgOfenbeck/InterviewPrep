package leetcode.p169

object Solution {
  def majorityElement(nums: Array[Int]): Int = {
    val count = scala.collection.mutable.Map.empty[Int, Int]

    var max = -1
    var maxnum = nums(0)
    for (ele <- nums) {
      count.updateWith(ele)(valueoption =>
        valueoption match {
          case None => Some(1)
          case Some(value) => {
            if (value + 1 > max){
                max  = value + 1
                maxnum = ele
            }
                
            Some(value + 1)
          }
        }
      )
    }
    maxnum

  }
}
