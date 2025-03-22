package leetcode.mock33.task2

object Solution {
  def nextGreaterElement(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {

    // a map => nums1(i) => index in nums2
    // nums2 -> next greater element

    val nums2Map = nums2.zipWithIndex.toMap

    val num2Next = scala.collection.mutable.Map.empty[Int, Int]
    val stack = scala.collection.mutable.Stack.empty[Int]

    stack.push(nums2(0))
    for (i <- (1 until nums2.size)) {

      val cur = nums2(i)
      if (cur > stack.top) {
        while (stack.nonEmpty && stack.top < cur) {
          num2Next(stack.pop) = cur
        }
      }
      stack.push(cur)
    }

    val res = Array.fill(nums1.size)(-1)

    for (i <- 0 until nums1.size) {
      val n2pos = nums2Map(nums1(i))
      val n2num = nums2(n2pos)
      num2Next.get(n2num) match {
        case Some(next) => res(i) = next
        case None       => res(i) = -1
      }
    }
    res
  }
}
