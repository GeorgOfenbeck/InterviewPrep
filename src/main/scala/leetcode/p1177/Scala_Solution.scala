package scala.leetcode.p1177

import scala.collection.immutable._

object Scala_Solution {
  def canMakePaliQueries(s: String, queries: Array[Array[Int]]): Array[Boolean] = {
    val stateAtPos = toCountMap(s)
    val answers = new Array[Boolean](queries.size)
    var i = 0;
    for (query <- queries) {
      val start = query(0)
      val stop = query(1) //inclusive
      val k = query(2)

      val diff = if (start == 0) stateAtPos(stop)
      else makeDiff(stateAtPos(start - 1), stateAtPos(stop))
      var nrfixes = 0
      var nrunpaired = 0
      for ( (k,v) <- diff) {
        if (!Character.isLowerCase(k)) { //needs to be fixed
          nrfixes = nrfixes + v
        }
        if (Character.isLowerCase(k) && v % 2 != 0) nrunpaired = nrunpaired + 1
      }
      val reqfixes = nrunpaired / 2
      if (reqfixes + nrfixes > k) answers(i) = false
      else answers(i) = true
      i = i + 1
    }
    answers
  }

   def toCountMap(s: String) = {
    //val res = new ArrayList[ImmutableMap[Character, Integer]](s.length)
     val res = new Array[scala.collection.immutable.Map[Char,Int]](s.size);
    for (i <- 0 until s.length) {
      val c  = s(i)
      if (i == 0) {
        val state = Map( c -> 1)
        res(i) = state
      }
      else {
        val prev = res(i - 1)
        val state = if (prev.contains(c)) prev + ( c -> (prev(c) + 1)) else prev + ( c -> 1)
        res(i) = state
      }
    }
    res
  }

  def makeDiff(a: Map[Char,Int], b: Map[Char,Int]): Map[Char,Int] = {
     val mutate = new scala.collection.mutable.HashMap[Char,Int]();
    for ( (k,v) <- b)
      {
        if (a.contains(k))
          mutate.put(k, v - a(k))
        else
          mutate.put(k,v)
      }
    mutate.toMap
  }


}
