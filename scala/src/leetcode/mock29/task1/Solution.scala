package leetcode.mock29.task1

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
trait NestedInteger {

  // Return true if this NestedInteger holds a single integer, rather than a nested list.
  def isInteger: Boolean

  // Return the single integer that this NestedInteger holds, if it holds a single integer.
  def getInteger: Int

  // Set this NestedInteger to hold a single integer.
  def setInteger(i: Int): Unit

  // Return the nested list that this NestedInteger holds, if it holds a nested list.
  def getList: Array[NestedInteger]

  // Set this NestedInteger to hold a nested list and adds a nested integer to it.
  def add(ni: NestedInteger): Unit
}

object Solution {
  def depthSumInverse(nestedList: List[NestedInteger]): Int = {

    val maxDepth = findMaxDepth(nestedList.toArray, 1)
    sumUp(nestedList.toArray, 1, maxDepth)
  }

  def sumUp(
      nestedList: Array[NestedInteger],
      depth: Int,
      maxDepth: Int
  ): Int = {
    nestedList.map { item =>
      if (item.isInteger) (maxDepth - depth + 1) * item.getInteger
      else sumUp(item.getList, depth + 1, maxDepth)
    }.sum
  }

  def findMaxDepth(nestedList: Array[NestedInteger], depth: Int): Int = {
    nestedList.map { item =>
      if (item.isInteger) depth
      else findMaxDepth(item.getList, depth + 1)
    }.max
  }
}
