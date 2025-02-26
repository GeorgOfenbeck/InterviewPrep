package leetcode.mock16.task1

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

object Solution {
  def anagramMappings(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {

    val nums2Map = arrToPos(nums2)
    val result = Array.ofDim[Int](nums1.size)

    var updated = nums2Map
    for (i <- 0 until nums1.size) {
      val (mappedTo, newmap) = dec(nums1(i), updated)
      updated = newmap
      result(i) = mappedTo
    }

    result
  }

  // map value -> indicies
  def arrToPos(nums: Array[Int]): Map[Int, Set[Int]] = {
    val mmap = mu.Map.empty[Int, Set[Int]]
    for (i <- 0 until nums.size) {
      mmap.updateWith(nums(i))(opt =>
        opt match {
          case None        => Some(Set(i))
          case Some(value) => Some(value + i)
        }
      )
    }
    mmap.toMap
  }

  def dec(k: Int, mapping: Map[Int, Set[Int]]): (Int, Map[Int, Set[Int]]) = {
    var idx = -1
    val updated = mapping.updatedWith(k)(opt =>
      opt match {
        case None => None
        case Some(value) => {
          idx = value.head
          if (value.size == 1) None else Some(value - idx)
        }
      }
    )
    (idx, updated)
  }

}
