package leetcode.p215

import scala.util.Random
import scala.annotation.tailrec


object Solution { 
  def findKthLargest(nums: Array[Int], k: Int): Int = {
   rec(nums.toList, k)
  }

  def rec(nums: List[Int], k: Int): Int ={
    val pivotidx = (Math.random()*nums.size).toInt 
    val pivot = nums(pivotidx)

    var bigger = List.empty[Int]
    var same = List.empty[Int]
    var smaller = List.empty[Int] 

    
    for(ele <- nums){
        if (ele > pivot){
            bigger = ele +: bigger
        } else {
            if (ele < pivot){
                smaller = ele +: smaller 
            } else {
                same = ele +: same
            }
        }
    }

    if (bigger.size >= k){
        rec(bigger, k)
    } else {
        if (bigger.size + same.size < k){
            rec(smaller, k - bigger.size - same.size)
        } else{
            pivot
        }
    }
  }
}

object Solution2 { 
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val targetidx = nums.size - k
    var lb = 0
    var up = nums.size
    var idx = partion(lb, nums.size, nums)

    while (idx != targetidx) {
      if (idx > targetidx) {
        idx = partion(lb, idx, nums )
      } else {
        idx = partion(idx + 1, up, nums)
      }
    }
    nums(idx)
  }

  // returns the idx of the pivot element
  // lb inclusiv
  // up exclusiv
  def partion(lb: Int, up: Int, arr: Array[Int]): Int = {
    val diff = up - lb
    val pivot_init = (Math.random()*diff).toInt + lb

    var left = lb
    var right = up

    swap(pivot_init, right - 1, arr) // move pivot to the very right
    val pivot = arr(right - 1)
    right = right - 1

    while (left < right) {
      val ele = arr(left)
      if (ele < pivot) {
        left = left + 1
      } else {
        swap(right - 1, left, arr)
        right = right - 1
      }
    }
    swap(left, up - 1, arr) // swap in the pivot
    left // retur pivot idx
  }
  def swap(i: Int, j: Int, a: Array[Int]): Unit = {
    val tmp = a(i)
    a(i) = a(j)
    a(j) = tmp
  }
}


object XXX{

  @main
  def main() = {
    val size = 10
    val arr: Array[Int] = new Array[Int](size)
    for (i <- 0 until size) {
      arr(i) = Random().between(0, 100)
      print(s"${arr(i)} - ")
    }
    /*
    val pividx = partion(0, 10, arr)
    println
    println(s"idx $pividx value ${arr(pividx)}")
    for (i <- 0 until 10) {
      print(s"${arr(i)} - ")
    }
      */
    println
    println(s"${Solution.findKthLargest(arr,1)}")  
  }
}
