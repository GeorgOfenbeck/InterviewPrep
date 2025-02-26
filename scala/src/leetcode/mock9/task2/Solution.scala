package leetcode.mock9.task2

import scala.collection.{mutable => mu}

object Solution {
  def kEmptySlots(bulbs: Array[Int], k: Int): Int = {
    val tree = mu.TreeSet.empty[Int]

    var found = false
    var i = 0

    while (i < bulbs.size && !found) {
      val cur = bulbs(i)
      tree.add(cur)
      val before = tree.maxBefore(cur)
      val after = tree.minAfter(cur + 1)
      found = check(tree, Some(cur), k) || check(tree, before, k) || check(
        tree,
        after,
        k
      )

      i = i + 1
    }
    if (found) i else -1

  }

  def check(tree: mu.TreeSet[Int], check: Option[Int], k: Int): Boolean = {
    check match {
      case None => false
      case Some(cur) => {
        val before = tree.maxBefore(cur)
        val after = tree.minAfter(cur + 1)
        (before) match {
          case (Some(b)) => {
            if ((cur - b) == (k + 1)) {
              true
            } else
              false
          }
          case _ => false
        }
        (after) match {
          case (Some(a)) => {
            if ((a - cur) == (k + 1)) {
              true
            } else
              false
          }

          case _ => false
        }
      }
    }

  }
}
