package leetcode.p873

import scala.collection.{mutable => mu}

object Solution {
  def lenLongestFibSubseq(arr: Array[Int]): Int = {

    val lcs = Array.ofDim[Int](arr.size, arr.size)

    val posMap = arr.toVector.zipWithIndex.toMap

    var max = 0
    for (i <- 0 until arr.size - 1) {
      for (j <- i + 1 until arr.size) {
        val a = arr(i)
        val b = arr(j)
        val posOp = posMap.get(a + b)
        posOp.map { idx =>
          val us = lcs(i)(j)
          val sofar = lcs(j)(idx)

          if (us + 1 > sofar) {
            lcs(j)(idx) = us + 1
            if (us + 1 > max) {
              max = us + 1
            }
          }
        }
      }
    }
    if (max != 0) max + 2 else max

  }

  def lenLongestFibSubseq2(arr: Array[Int]): Int = {
    val lcs = Array.ofDim[Int](arr.size)

    val posMap = arr.toVector.zipWithIndex.toMap

    var max = 0
    for (i <- 0 until arr.size - 1) {
      for (j <- i + 1 until arr.size) {
        val a = arr(i)
        val b = arr(j)
        val posOp = posMap.get(a + b)
        posOp.map { idx =>
          println(s"a: $a, b: $b, idx: $idx, x: ${arr(idx)}")
          val sofar = lcs(idx)
          val us = lcs(i)
          if (us + 2 > sofar) {
            lcs(idx) = us + 2
            if (us + 2 > max) {
              max = us + 2
            }
          }
        }
      }
    }
    println(lcs.mkString(" "))
    if (max != 0) max + 1 else max
  }

  @main
  def test(): Unit = {
    println(
      lenLongestFibSubseq(Array(2, 4, 5, 6, 7, 8, 11, 13, 14, 15, 21, 22, 34))
    )
  }
}
