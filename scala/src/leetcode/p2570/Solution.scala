package leetcode.p2570

case class IdVal(id: Int, value: Int)

object Solution {
  def mergeArrays(
      nums1: Array[Array[Int]],
      nums2: Array[Array[Int]]
  ): Array[Array[Int]] = {

    val first = nums1
      .map(x => IdVal(x(0), x(1)))
      .groupMapReduce(x => x.id)(x => x.value)((a, b) => a)
    val second = nums2
      .map(x => IdVal(x(0), x(1)))
      .groupMapReduce(x => x.id)(x => x.value)((a, b) => a)

    first.keySet
      .union(second.keySet)
      .toArray
      .sorted
      .map { id =>
        val firstVal = first.get(id).getOrElse(0)
        val secondVal = second.get(id).getOrElse(0)
        Array(id, firstVal + secondVal)
      }
      .toArray

  }
}
