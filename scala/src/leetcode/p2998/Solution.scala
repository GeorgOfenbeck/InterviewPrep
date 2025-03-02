package leetcode.p2998

object Solution {
  def minimumOperationsToMakeEqual(x: Int, y: Int): Int = {

    val queue = scala.collection.mutable.Queue[(Int, Int, Int)]()

    val visited = scala.collection.mutable.Set[(Int, Int)]()
    queue.enqueue((x, y, 0))

    var found: Option[Int] = None
    while (queue.nonEmpty && found.isEmpty) {
      val (a, b, count) = queue.dequeue()
      if (a == b) {
        found = Some(count)
      }
      if (!visited.contains((a, b))) {
        visited.add((a, b))
        if (a % 11 == 0) {
          queue.enqueue((a / 11, b, count + 1))
        }
        if (a % 5 == 0) {
          queue.enqueue((a / 5, b, count + 1))
        }
        queue.enqueue((a + 1, b, count + 1))
        queue.enqueue((a - 1, b, count + 1))
      }
    }
    found.getOrElse(-1)
  }

}
