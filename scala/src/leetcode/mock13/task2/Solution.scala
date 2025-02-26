package leetcode.mock13.task2

import scala.collection.{mutable => mu}

class NumArray(_nums: Array[Int]) {

  val tree = Array.fill(4 * _nums.length)(0)

  createRangeTree(_nums, tree, 0, 0, _nums.length - 1)

  def update(index: Int, `val`: Int): Unit = {
    updateSegTree(tree, 0, 0, _nums.length - 1, index, `val`)
  }

  def sumRange(left: Int, right: Int): Int = {
    querySegTree(tree, 0, 0, _nums.length - 1, left, right)
  }

  def querySegTree(
      tree: Array[Int],
      idx: Int,
      lo: Int,
      hi: Int,
      i: Int,
      j: Int
  ): Int = {
    if (lo > j || hi < i) {
      return 0
    }

    if (i <= lo && j >= hi) {
      tree(idx)
    }

    val mid = lo + (hi - lo) / 2

    if (i > mid) {
      return querySegTree(tree, 2 * idx + 2, mid + 1, hi, i, j)
    } else if (j <= mid) {
      return querySegTree(tree, 2 * idx + 1, lo, mid, i, j)
    }

    val left = querySegTree(tree, 2 * idx + 1, lo, mid, i, mid)
    val right = querySegTree(tree, 2 * idx + 2, mid + 1, hi, mid + 1, j)

    return merge(left, right)
  }

  def updateSegTree(
      tree: Array[Int],
      idx: Int,
      lo: Int,
      hi: Int,
      arrIndex: Int,
      value: Int
  ): Unit = {
    if (lo == hi) {
      tree(idx) = value
    }
    val mid = lo + (hi - lo) / 2

    if (arrIndex > mid) {
      updateSegTree(tree, 2 * idx + 2, mid + 1, hi, arrIndex, value)
    } else {
      updateSegTree(tree, 2 * idx + 1, lo, mid, arrIndex, value)
    }

    tree(idx) = merge(tree(2 * idx + 1), tree(2 * idx + 2))
  }

  @inline
  def merge(a: Int, b: Int): Int = {
    a + b
  }

  def createRangeTree(
      arr: Array[Int],
      tree: Array[Int],
      idx: Int,
      lo: Int,
      hi: Int
  ): Unit = {
    if (lo == hi) {
      tree(idx) = arr(lo)
      return
    } else {
      val mid = lo + (hi - lo) / 2
      createRangeTree(arr, tree, 2 * idx + 1, lo, mid)
      createRangeTree(arr, tree, 2 * idx + 2, mid + 1, hi)
      tree(idx) = merge(tree(2 * idx + 1), tree(2 * idx + 2))
    }

  }

}

object Solution {
  @main
  def test(): Unit = {
    // arr[] = { 18, 17, 13, 19, 15, 11, 20, 12, 33, 25 };
    val nArray = new NumArray(Array(18, 17, 13, 19, 15, 11, 20, 12, 33, 25))
    println(nArray.tree.toList)
  }
}
