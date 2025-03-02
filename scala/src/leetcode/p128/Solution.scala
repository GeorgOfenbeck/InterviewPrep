package leetcode.p128

import scala.collection.{mutable => mu}

object Solution {
  def longestConsecutive(nums: Array[Int]): Int = {

    val parent = mu.Map.empty[Int, Int]

    for (n <- nums) {
      merge(parent, n)
    }

    println(parent)
    3
  }

  def merge(umap: mu.Map[Int, Int], a: Int): Unit = {

    val parent = umap.updateWith(a)(opt =>
      opt match {
        case Some(p) => Some(p)
        case None => {
          // not in the union yet
          // next bigger number is in the union
          if (umap.contains(a + 1)) {
            var next = a + 1
            var parent = umap(next)
            while (parent != next) {
              next = parent
              parent = umap(next)
            }
            // find the root
            // update parent to be next biggers parent
            Some(parent)
          } else {
            Some(a)
          }
        }
      }
    )

    umap.updateWith(a)(opt =>
      opt match {
        case None => assert(false)
        case Some(aparent) => {
          // check if there is a union to the bottom

          if (umap.contains(a - 1)) {
            var prev = a - 1
            var parent = umap(prev)
            while (parent != prev) {
              prev = parent
              parent = umap(prev)
            }
            // find the root
            // update parent to be next biggers parent
            umap.update(parent, aparent)
          }
          Some(aparent)
        }
      }
    )
  }

  @main
  def test(): Unit = {
    val nums = Array(100, 4, 200, 1, 3, 2)
    val res = longestConsecutive(nums)
    println(res)
  }

}
