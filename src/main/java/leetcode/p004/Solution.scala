package leetcode.p004

import scala.collection.Searching

object Solution_Scala {

  def main(args: Array[String] ):Unit = {
    val x= Array(1,2,4)
    val y = Array(3,5,6)
    val res = findMedianSortedArrays(x,y)
    val z = (x ++ y).sorted
    if (z.size % 2 == 0)
      println(s"$res ${ (z(z.size/2) + z(z.size/2)-1 )/2.0}")
    else
      println(s"$res ${ z(z.size/2).toDouble }")
  }


  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val xlen = nums1.size
    val ylen = nums2.size

    val zlen = xlen + ylen

    if (zlen % 2 == 0) {
      val first = findNthElement(zlen / 2, nums1.toVector, nums2.toVector)
      val second = findNthElement(zlen / 2 - 1, nums1.toVector, nums2.toVector)
      (first + second) / 2.0;
    }
    else
      findNthElement(zlen / 2, nums1.toVector, nums2.toVector)
  }


  def findNthElement(n: Int, nums1: Vector[Int]): Int = {
    nums1(n)
  }

  def findNthElement(n: Int, nums1: Vector[Int], nums2: Vector[Int]): Int = {
    if (nums1.isEmpty) findNthElement(n, nums2)
    else if (nums2.isEmpty) findNthElement(n, nums1)
    else {
      val (xl, xmed, xh) = chop(nums1)
      val ypos = findPos(xmed,nums2)
      if (xl.size + ypos == n)
        xmed
      else {
        if (xl.size + ypos > n)
          findNthElement(n, xl,nums2.slice(0,ypos))
        else
          findNthElement(n - (xl.size + ypos)-1, xh,nums2.slice(ypos,nums2.length))
      }
    }
  }

  def findPos(n: Int, nums: Vector[Int]) = {
    import scala.collection.Searching._
    val res = nums.search(n)
    res.insertionPoint
  }

  def chop(arr: Vector[Int]): (Vector[Int], Int, Vector[Int]) = {
    if (arr.length == 1)
      (Vector.empty, arr(0), Vector.empty)
    else if (arr.length == 2)
      (Vector.empty, arr.head, arr.tail)
    else
      (arr.slice(0, arr.length / 2), arr(arr.length / 2), arr.slice(arr.length / 2 + 1, arr.length))
  }
}
