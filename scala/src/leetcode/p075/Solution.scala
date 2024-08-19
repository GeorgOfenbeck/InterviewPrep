package leetcode.p075

object Solution {
  def sortColors(nums: Array[Int]): Unit = {

    var i = 0
    var z = 0 // 0th ele
    var t = nums.size // last ele

    while (i < t) {
      nums(i) match {
        case 0 => {
          if (i > z) {
            swap(i, z, nums)
            z = z + 1
          } else {
            i = i + 1
            z = z + 1
          }
        }
        case 1 => {
          i = i + 1
        }
        case 2 => {
          swap(i, t - 1, nums)
          t = t - 1
        }
      }
    }
  }
  
  def swap(a: Int, b: Int, arr: Array[Int]) = {
    val tmp = arr(a)
    arr(a) = arr(b)
    arr(b) = tmp
  }
}
