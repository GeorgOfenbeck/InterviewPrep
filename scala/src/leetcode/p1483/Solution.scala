package leetcode.p1483

import scala.collection.{mutable => mu}
import scala.annotation.tailrec

class TreeAncestor(_n: Int, _parent: Array[Int]) {

  // idx => parent
  val parentMap =
    Map.empty[Int, Int] // _parent.zipWithIndex.map((a, b) => (b, a)).toMap

  def getKthAncestor(node: Int, k: Int): Int = {
    getKthAncestorA(node, k)
  }

  @tailrec
  private def getKthAncestorA(node: Int, k: Int): Int = {
    _parent(node) match {
      case -1 => -1
      case value => {
        if (k == 1) return value
        getKthAncestorA(value, k - 1)
      }
    }
  }

  @tailrec
  private def getKthAncestorR(node: Int, k: Int): Int = {
    parentMap.get(node) match {
      case None => -1
      case Some(value) => {
        if (value == -1) return value
        if (k == 0) return value
        getKthAncestorR(value, k - 1)
      }
    }

  }

}
