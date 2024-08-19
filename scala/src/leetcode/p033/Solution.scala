package leetcode.p033

import scala.collection.Searching.SearchResult
import scala.collection.Searching.InsertionPoint
import scala.collection.Searching.Found

object Solution {
  def search(nums: Array[Int], target: Int): Int = {
    if (nums.isEmpty) return -1
    val shift = binsearch(0, nums.size, nums, None, nums(0))
    val shiftidx = shift match {
      case Found(foundIndex)              => foundIndex
      case InsertionPoint(insertionPoint) => 0
    }
    val sarray = new ShiftedArray(nums, shiftidx)
    
    sarray.search(target, 0, nums.size)(Ordering.Int) match {
        case Found(foundIndex) => { (foundIndex + shiftidx ) % nums.size 
        } 
        case InsertionPoint(insertionPoint) => -1
    }
  }

  class ShiftedArray(nums: Array[Int], shift: Int) extends IndexedSeq[Int] {
    def apply(i: Int): Int = {
      nums((i + shift) % nums.size)
    }
    def length: Int = nums.length
  }

  def binsearch(
      l: Int,
      r: Int,
      arr: Array[Int],
      found: Option[Int],
      zeroele: Int
  ): SearchResult = {
    if (r <= l) {
      found match {
        case None        => return InsertionPoint(l)
        case Some(value) => return Found(value)
      }
    } else {
      val idx = l + (r - l - 1) / 2
      val check = arr(idx)
      if (check >= zeroele) {
        binsearch(idx + 1, r, arr, found, zeroele)
      } else {
        binsearch(l, idx, arr, Some(idx), zeroele)
      }
    }
  }
}


object test{
    @main
    def main ()={
        val arr = Array(4,5,6,7,0,1,2)
        //val arr = Array(1,3)
        println(Solution.search(arr, 0))
    }
}