package leetcode.mock28.task1

import scala.collection.{mutable => mu}

object Solution {
  def simplifyPath(path: String): String = {
    val split = path.split("/").toVector.filter(_.nonEmpty)
    val dirStack = mu.Stack.empty[String]

    for (seg <- split) {
      seg match {
        case "."  =>
        case ".." => if (!dirStack.isEmpty) dirStack.pop()
        case _    => dirStack.push(seg)
      }
    }

    var res = Vector.empty[String]
    while (dirStack.nonEmpty) {
      res = dirStack.pop() +: res
    }
    "/" + res.mkString("/")
  }
}
