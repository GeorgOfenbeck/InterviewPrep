package leetcode.p560

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def subarraySum(nums: Array[Int], k: Int): Int = {
    val prefixSum = Array.fill(nums.size)(0)
    val posMap = mu.Map.empty[Int, Set[Int]]
    var count = 0

    prefixSum(0) = nums(0)
    if (nums(0) == k) count += 1
    posMap.addOne(nums(0) -> Set(0))

    for (i <- 1 until nums.size) {
      prefixSum(i) = prefixSum(i - 1) + nums(i)

      if (prefixSum(i) == k) count += 1
      if (posMap.contains(prefixSum(i) - k)) {
        count += posMap(prefixSum(i) - k).size
      }
      posMap.updateWith(prefixSum(i))(opt =>
        opt match {
          case None        => Some(Set(i))
          case Some(value) => Some(value + i)
        }
      )
    }
    count
  }

  @main
  def test(): Unit = {
    println(subarraySum(Array(-1, -1, 1), 0)) // 2
  }
}
