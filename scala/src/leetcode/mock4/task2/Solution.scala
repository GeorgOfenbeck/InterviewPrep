package leetcode.mock4.task2
import scala.collection.{mutable => mu}

object Solution {
  def generateParenthesis(n: Int): List[String] = {
    val res = mu.Set.empty[String]
    rec(Vector.empty, n, 0, res)
    res.toList
  }

  def rec(
      sofar: Vector[Char],
      available: Int,
      open: Int,
      res: mu.Set[String]
  ): Unit = {
    // we used all - closed all
    if (open == 0 && available == 0) {
      res.addOne(sofar.mkString)
      return
    }
    if (open > 0) {
      rec(sofar :+ ')', available, open - 1, res)
    }
    if (available > 0) {
      rec(sofar :+ '(', available - 1, open + 1, res)
    }
  }
}
