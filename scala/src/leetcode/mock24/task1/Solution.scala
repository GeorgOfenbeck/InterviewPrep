package leetcode.mock24.task1

import scala.collection.{mutable => mu}
import scala.util.control.Breaks._

object Solution {
  def pancakeSort(arr: Array[Int]): List[Int] = {

    val ordering: Ordering[(Int, Int)] = Ordering.by[(Int, Int), Int](_._1)
    val queue = mu.PriorityQueue.from(arr.toVector.zipWithIndex)(ordering)

    var res: List[Int] = List.empty

    var maxIndex = 0
    var upperBound = arr.length
    for (i <- 0 until arr.size) {

      res = (maxIndex + 1) :: res
      res = (upperBound) :: res
      upperBound -= 1
    }

    res
  }
}
