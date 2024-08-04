package leetcode.p049

import java.util.BitSet
import java.{util => ju}
import java.{util => ju}

object Solution {
  @main
  def main() = {
    val strs = Array("hhhhu", "tttti", "tttit", "hhhuh", "hhuhh", "tittt")
    groupAnagrams(strs)
  }

  def groupAnagrams(strs: Array[String]): List[List[String]] = {

    val fingerprints = strs.map { x =>
      FingerPrint(x)
    }

    val map = scala.collection.mutable.HashMap.empty[ju.BitSet, List[String]]
    val smap = scala.collection.mutable.HashMap.empty[MultPrint, List[String]]

    for (f <- fingerprints) {
      if (f.hasMulti) {

        if (smap.contains(f.multprint)) {
          val prev = smap.get(f.multprint).get
          smap.put(f.multprint, f.str +: prev)
        } else {
          smap.put(f.multprint, List(f.str))
        }

      } else {
        if (map.contains(f.bitSet)) {
          val prev = map.get(f.bitSet).get
          map.put(f.bitSet, f.str +: prev)
        } else {
          map.put(f.bitSet, List(f.str))
        }
      }
    }

    val ret: List[List[String]] =
      map.values.foldLeft(List.empty[List[String]]) { (acc, ele) =>
        ele +: acc
      }

    smap.values.foldLeft(ret) { (acc, ele) =>
      ele +: acc
    }

  }

  class MultPrint(val str: String) {
    var farray: Array[Int] = null
    farray = new Array[Int](28)
    for (c <- str) {
      if (c.isValidChar) {
        val l = c.toLower
        val pos = c.toInt - 'a'.toInt
        farray.hashCode()
        farray(pos) = farray(pos) + 1
      }
    }
    override def hashCode(): Int = java.util.Arrays.hashCode(farray)
    override def equals(x: Any): Boolean = if (x.isInstanceOf[MultPrint]) {
      val y = x.asInstanceOf[MultPrint]
      java.util.Arrays.equals(this.farray, y.farray)
    } else
      false
  }

  class FingerPrint(val str: String) {
    var multprint: MultPrint = null
    val bitSet: ju.BitSet = new ju.BitSet(32)
    var hasMulti = false
    for (c <- str) {
      if (c.isValidChar) {
        val l = c.toLower
        val pos = c.toInt - 'a'.toInt
        if (bitSet.get(pos) == true) {
          hasMulti = true
        } else
          bitSet.set(pos, true)
      }
    }
    if (hasMulti) {
      multprint = new MultPrint(str)
    }
  }
}
