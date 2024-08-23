package leetcode.p380

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class RandomizedSet() {
  val set = scala.collection.mutable.HashMap.empty[Int, Int]
  val ad = scala.collection.mutable.ArrayBuffer.empty[Int]
  var size = 0

  def insert(`val`: Int): Boolean = {
    if (!set.contains(`val`)) {
      set.put(`val`, size)
      if (size == ad.size)
        ad.append(`val`)
      else
        ad.update(size, `val`)
      size = size + 1
      true
    } else { false }
  }

  def remove(`val`: Int): Boolean = {
    set.get(`val`) match {
      case None => false
      case Some(value) => {
        val last = ad(size - 1)
        ad.update(value, last)
        set.update(last, value)
        set.remove(`val`)
        size = size - 1
        true
      }
    }
  }

  def getRandom(): Int = {
    val idx = Random.between(0, size)
    return ad(idx)
  }

}

object blub {
  @main
  def main() = {
    val r = RandomizedSet()
    r.insert(3)
    r.remove(3)
    r.remove(3)
    r.insert(3)
    r.getRandom()
    r.remove(0)
  }

}

/** Your RandomizedSet object will be instantiated and called as such: val obj =
  * new RandomizedSet() val param_1 = obj.insert(`val`) val param_2 =
  * obj.remove(`val`) val param_3 = obj.getRandom()
  */
