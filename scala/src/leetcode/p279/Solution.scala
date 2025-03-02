package leetcode.p279

object Solution {
  def numSquares(n: Int): Int = {
    val perfectSquares = getPerfectSquares(n)

    println(perfectSquares.mkString(","))
    val visited: Array[Option[Int]] = Array.fill(n + 1)(None)
    val queue = scala.collection.mutable.Queue((n, 0))
    var found: Option[Int] = None

    while (queue.nonEmpty && found.isEmpty) {
      val (cur, steps) = queue.dequeue()
      if (visited(cur).nonEmpty) {
        // println(s"skip $cur")
      } else {
        visited(cur) = Some(steps)
        // skip
        if (cur == 0) {
          found = Some(steps)
        } else {
          for (ele <- perfectSquares) {
            if (cur - ele >= 0)
              if (visited(cur - ele).isEmpty) {
                queue.enqueue((cur - ele, steps + 1))
              }
          }
          // println(visited.mkString(","))
        }
      }
    }

    found.getOrElse(-1)

  }

  def getPerfectSquares(n: Int): Set[Int] = {
    var i = 1
    var squares = Set.empty[Int]
    while (i * i <= n) {
      squares += i * i
      i = i + 1
    }
    squares
  }

  @main
  def test(): Unit = {
    println(numSquares(12))
  }
}
