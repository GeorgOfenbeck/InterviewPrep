package scala.leetcode.p028

import scala.collection.immutable._
import scala.collection.mutable.ArrayBuffer

object Solution {
  def strStr(haystack: String, needle: String): Int = {
    haystack.indexOf(needle)


    val hmap = new HashMap[Int, String]()

    val x = hmap + (3 -> "hallo")

    val v = Vector(1,2,3,4)

    val v1 = v :+ 1

    val s = HashSet(1,2,3,4)


    2


    }
  /*
  def strStr(haystack: String, needle: String): Int = {
    if (needle == null || haystack == null || haystack.size < needle.size) 0
    else
    if (needle.size == 0) 0
    else {
      var npos = 0
      var hpos = 0
      var startpos = 0

      while (npos < needle.size && hpos < haystack.size)
      {
        if (haystack(hpos) == needle(npos))
        {
          hpos = hpos + 1
          npos = npos + 1
        } else
        {
          npos = 0
          if (haystack(hpos) == needle(npos))
          {
            startpos = hpos
            hpos = hpos + 1
            npos = npos + 1
          } else
            hpos = hpos + 1
        }
      }
      if (npos == needle.size)
        startpos
      else
        -1
    }
  } */
}