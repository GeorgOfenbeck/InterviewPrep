package leetcode.mock32.task2

object Solution {
  def customSortString(order: String, s: String): String = {

    var freq = s.groupMapReduce(s => s)(s => 1)(_ + _)

    val sb = new StringBuilder
    for (c <- order) {
      freq.get(c) match {
        case Some(v) => {
          for (i <- 0 until v)
            sb.append(c)
          freq = freq - c
        }
        case None => ()
      }
    }
    for ((k, v) <- freq) {
      for (i <- 0 until v)
        sb.append(k)
    }
    sb.toString()
  }
}
