package leetcode.p3376

import scala.collection.{mutable => mu}

object Solution {
  val pr = false
  case class Lock(strength: Int, idx: Int)

  def findMinimumTime_greedy(strength: List[Int], k: Int): Int = {

    if (strength.isEmpty) return 0
    if (strength.size == 1) return strength.head
    val ordering: Ordering[Lock] = new Ordering[Lock] {
      override def compare(x: Lock, y: Lock): Int = {
        if (x.strength == y.strength) {
          return x.idx - y.idx
        } else {
          return x.strength - y.strength
        }
      }
    }
    val minTree = mu.TreeSet.empty[Lock](ordering)

    for (lock <- strength.zipWithIndex) {
      minTree.addOne(Lock(lock._1, lock._2))
    }

    var total_time = 0
    var x = 1
    while (!minTree.isEmpty && minTree.size > 2) {
      val time = timeForMinLock(minTree, x)
      val sword = time * x
      if (pr)
        println(s"sword: ${sword}, time: ${time}, x: ${x}")
      removeBiggestPossibleLock(sword, minTree)
      x = x + k
      total_time = total_time + time
    }

    return lastTwo(minTree.head, minTree.last, k, x) + total_time
    // return total_time
  }

  def lastTwo(a: Lock, b: Lock, k: Int, x: Int): Int = {

    val aa = x + k

    val timefa = (a.strength + (x - 1)) / x
    val timetb = (b.strength + (aa - 1)) / aa + timefa

    val timefb = (b.strength + (x - 1)) / x
    val timeta = (a.strength + (aa - 1)) / aa + timefb

    Math.min(timeta, timetb)
  }

  def removeBiggestPossibleLock(
      sword: Int,
      tree: mu.TreeSet[Lock]
  ): Unit = {
    val biggest = tree.maxBefore(Lock(sword + 1, 0))
    biggest match {
      case Some(lock) => {
        if (pr)
          println(s"removing ${lock}")
        tree.remove(lock)
      }
      case None => {
        assert(false, "should not be possible")
      }
    }
  }

  def timeForMinLock(tree: mu.TreeSet[Lock], x: Int): Int = {
    val smallest = tree.head
    return (smallest.strength + (x - 1)) / x
    // if (time * x == smallest.strength) {
    //   return time
    // } else {
    //   return time + 1
    // }
  }

  def findMinimumTime(strength: List[Int], k: Int): Int = {

    val cache = mu.Map.empty[List[Int], Int]
    val permutations = strength.permutations
//    List(List(3, 7, 6, 22, 18, 50)).map { perm =>
    permutations.map { perm =>
      calcTime(cache, perm, k)
    }.min

  }

  def calcTime(cache: mu.Map[List[Int], Int], perm: List[Int], k: Int): Int = {
    val x = 1 + (k * (perm.size - 1))
    if (perm.size == 1)
      return (perm.head + (x - 1)) / x

    if (cache.contains(perm)) return cache(perm)

    val tailcost = calcTime(cache, perm.tail, k)
    val headcost = (perm.head + (x - 1)) / x

    cache.addOne(perm, headcost + tailcost)
    headcost + tailcost

  }

  def findMinimumTime2(strength: List[Int], K: Int): Int =
    // List(List(3, 7, 6, 22, 18, 50)).map { perm =>
    strength.permutations.map { perm =>
      val res = perm
        .foldLeft((0, 1)) { case ((t, x), s) =>
          (t + (s + (x - 1)) / x, x + K)
        }
        ._1

      if (res == 30) {
        println(perm)
      }
      res

    }.min

  @main
  def test(): Unit = {
    // println(findMinimumTime(List(1,2,3,4,5), 3))
    // println(findMinimumTime(List(2, 3, 1, 4, 2), 1))

//     println(findMinimumTime(List(2, 5, 4), 2))
//     println(findMinimumTime(List(3, 4, 1), 1))
//    println(findMinimumTime(List(7, 3, 6, 18, 22, 50), 4))
//    println(findMinimumTime2(List(7, 3, 6, 18, 22, 50), 4))
    // println(findMinimumTime(List(40, 50), 4))
    // println(findMinimumTime2(List(21, 22, 40, 12, 43, 21), 3))
    println(findMinimumTime2(List(21, 22, 40, 12, 43, 21), 3))
  }

}
