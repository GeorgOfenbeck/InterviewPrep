package leetcode.p2297

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

//neighbors: id -> cost
case class Node(val id: Int, neighbors: mu.Map[Int, Int])

object Solution {
  def minCost(nums: Array[Int], costs: Array[Int]): Long = {
    // length n
    // start at 0
    // i -> j with i < j
    // if nums[i] <= nums[j] && nums[k] < nums[i] for all k i < k < j

    // or
    //
    // nums[i] > nums[j] && nums[k] >= nums[i] for all k i < k < j
    //
    // cost[i] => cost to jump too i
    //
    //
    //

    val graph = mu.Map.empty[Int, Node]
    for (i <- 0 until nums.length) {
      val node = Node(i, mu.Map())
      graph.put(i, node)
    }
    // for (i <- 0 until nums.length) {
    //   inc(graph, i, i + 1, nums, costs)
    //   dec(graph, i, i + 1, nums, costs)
    // }
    //
    nextSmaller(nums, costs, graph)
    nextGreater(nums, costs, graph)
    assert(graph.map(x => x._2.neighbors.size).max < 3)
    val ordering = Ordering.by[(Int, Long), Long](_._2).reverse
    val queue = mu.PriorityQueue.empty[(Int, Long)](ordering)

    queue.enqueue((0, 0))

    val visited = mu.Set.empty[Int]

    var found: Option[Long] = None
    while (queue.nonEmpty && found.isEmpty) {
      val (id, cost) = queue.dequeue()
      if (id == costs.size - 1) {
        found = Some(cost)
      }
      for (edge <- graph(id).neighbors) {
        val (neighborId, neighborCost) = edge
        if (!visited.contains(neighborId)) {
          queue.enqueue((neighborId, cost + neighborCost))
        }
      }
    }
    found.getOrElse(-1)
  }

  def nextSmaller(
      nums: Array[Int],
      cost: Array[Int],
      graph: mu.Map[Int, Node]
  ): Unit = {
    val n = nums.size
    val result = Array.fill(n)(-1)
    val stack = mu.Stack.empty[Int]

    for (i <- 0 until n) {
      while (stack.nonEmpty && nums(stack.top) > nums(i)) {
        result(stack.pop()) = i
      }
      stack.push(i)
    }
    for (i <- 0 until n) {
      if (result(i) != -1) {
        graph(i).neighbors.put(result(i), cost(result(i)))
      }
    }
  }

  def nextGreater(
      nums: Array[Int],
      cost: Array[Int],
      graph: mu.Map[Int, Node]
  ): Unit = {
    val n = nums.size
    val result = Array.fill(n)(-1)
    val stack = mu.Stack.empty[Int]

    for (i <- 0 until n) {
      while (stack.nonEmpty && nums(stack.top) <= nums(i)) {
        result(stack.pop()) = i
      }
      stack.push(i)
    }
    for (i <- 0 until n) {
      if (result(i) != -1) {
        graph(i).neighbors.put(result(i), cost(result(i)))
      }
    }
  }

  @tailrec
  def inc(
      graph: mu.Map[Int, Node],
      i: Int,
      k: Int,
      nums: Array[Int],
      costs: Array[Int]
  ): Unit = {
    if (k == nums.size)
      return
    val numsi = nums(i)
    val numsk = nums(k)
    // val numsj = nums(j)

    if (numsk < numsi) {
      inc(graph, i, k + 1, nums, costs)
    } else {
      graph(i).neighbors.put(k, costs(k))
    }

  }

  @tailrec
  def dec(
      graph: mu.Map[Int, Node],
      i: Int,
      k: Int,
      nums: Array[Int],
      costs: Array[Int]
  ): Unit = {
    if (k == nums.size)
      return

    val numsi = nums(i)
    val numsk = nums(k)
    // val numsj = nums(j)

    if (numsk >= numsi) {
      dec(graph, i, k + 1, nums, costs)
    } else {
      graph(i).neighbors.put(k, costs(k))
    }

  }

  @main
  def test(): Unit = {
    val nums = Array(3, 2, 4, 4, 1)
    val costs = Array(3, 7, 6, 4, 2)
    val result = minCost(nums, costs)
    println(result)
  }

}
