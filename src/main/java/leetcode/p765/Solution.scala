package leetcode.p765

import scala.annotation.tailrec

/*
N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).

The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.

Example 1:

Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
Example 2:

Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.
Note:

len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.

 */
object Solution {

  case class Neigh(left: Int, right: Int){

    def compliment(smaller: Int): Int = {
      if (smaller % 2 == 0) smaller + 1
      else smaller - 1
    }
    def compliment_left: Int = compliment(left)
    def compliment_right: Int = compliment(right)

    def isComplete: Boolean = {
      left == compliment_right
    }

  }

  def minSwapsCouples(row: Array[Int]): Int = {
    val x = (0 until row.size/2).map( e => {
      val a = row(2*e)
      val b = row(2*e+1)
      Neigh(a,b)
    })

    val maps = x.foldLeft(Map.empty[Int,Neigh])( (acc,ele) => acc + (ele.left -> ele) + (ele.right -> ele))

    val toSwap = maps.filter {case (k,v)=> !v.isComplete}

    swapping(toSwap,0)

  }
  @tailrec
  def swapping(toSwap: Map[Int,Neigh], count: Int) : Int = {
    if (toSwap.isEmpty) count
    else {
      val (k, seats) = toSwap.head
      val search = seats.compliment_left

      val otherseat = toSwap(search)

      val swapout = seats.right

      val stay = if (otherseat.right == search) otherseat.left else otherseat.right

      val newOtherSeat = Neigh(swapout,stay)

      val t = scala.collection.immutable.HashMap(3 -> "hallo")
      val tt = t - 3

      val removeCompleted: Map[Int,Neigh] = toSwap - seats.left - seats.compliment_left

      val finalMap = if (newOtherSeat.isComplete)
        removeCompleted - swapout - stay
      else
        removeCompleted + (swapout -> newOtherSeat) + (stay -> newOtherSeat)
      swapping(finalMap,count + 1)
    }
  }
}