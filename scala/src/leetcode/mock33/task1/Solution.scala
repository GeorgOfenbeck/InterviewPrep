package leetcode.mock33.task1

object Solution {
  def arraysIntersection(
      arr1: Array[Int],
      arr2: Array[Int],
      arr3: Array[Int]
  ): List[Int] = {

    var i = 0
    var j = 0
    var k = 0
    var l = List.empty[Int]

    while (i < arr1.size && j < arr2.size && k < arr3.size) {
      val a = arr1(i)
      val b = arr2(j)
      val c = arr3(k)

      // they are the same -> add to list
      if (a == b && b == c) {
        l = a +: l
        i = i + 1
        j = j + 1
        k = k + 1
      } else {
        // one is smaller than the other -> move the smaller one
        val smallest = Vector(a, b, c).min
        if (a == smallest) i = i + 1
        if (b == smallest) j = j + 1
        if (c == smallest) k = k + 1
      }
    }
    l.reverse
  }
}
