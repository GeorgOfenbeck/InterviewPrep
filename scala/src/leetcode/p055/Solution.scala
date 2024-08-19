package leetcode.p055

import scala.annotation.tailrec

object Solution {
  def canJump(nums: Array[Int]): Boolean = {
    //rec(Set.empty, Set(0), nums) 
    jump(nums(0),nums, 0)
  }

  @tailrec
  def rec(visited: Set[Int], todo: Set[Int], nums: Array[Int]): Boolean = {
    if (todo.isEmpty)
        return false
    val headidx = todo.head
    if (headidx >= nums.size - 1) {
        if (headidx == nums.size - 1)
            return true
        else
            return rec(visited + headidx, todo - headidx, nums)
    } else {
      val headvalue = nums(headidx)
      var newtodo = todo
      for (target <- 1 to headvalue) {
        if (headidx + target < nums.size && !visited.contains(headidx + target)) {
          newtodo = newtodo + (headidx + target)
        }
      }
      return rec(visited + headidx, newtodo - headidx, nums)      
    }
  }

  @tailrec
  def jump(max: Int, nums:Array[Int], cur: Int): Boolean = {
    if (cur > max)
        return false
    val reach = cur + nums(cur)
    if (reach >= nums.size - 1)
        return true
    if (reach > max)
        jump(reach, nums, cur + 1)
    else
        jump(max, nums, cur + 1)
  }
}
