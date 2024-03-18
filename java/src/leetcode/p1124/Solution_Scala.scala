package leetcode.p1124

  object SSolution extends App{


    val x = Array(0,0,9,9,9)
    System.out.println(longestWPI(x))


    def longestWPI(hours: Array[Int]): Int = {
      if (hours == null || hours.length == 0) return 0
      if (hours.length == 1) return if (hours(0) > 8) 1 else 0

      val len = hours length

      var max = 0
      var preSum = 0
      import scala.collection.mutable.Map
      var map = Map[Int, Int]()
      map += 0 -> -1

      for (i <- 0 until len) {

        preSum = if (hours(i) > 8) preSum + 1 else preSum - 1
        System.out.println(map)
        System.out.println(preSum)
        if (preSum > 0) {
          max = Math.max(max, i + 1)
        } else if (map.contains(preSum - 1)) {
          max = Math.max(max, i - map(preSum - 1))
        }
        if (!map.contains(preSum)) map += preSum -> i
      }

      return max
    }
  }