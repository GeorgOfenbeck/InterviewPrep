package leetcode.p045

import scala.collection.{mutable => mu}

object Solution {
  def jump(nums: Array[Int]): Int = {
    dikIter(nums)
  }

  def dikIter(nums: Array[Int]): Int = {
    /*
     * each iteration
     *  - take head from working set (nodes to still consider)
     *      - working set should be ordered by number of jumps (asc)
     *  - mark all nodes reachable from head with jumps + 1 if they are not already visited
     *  - add these nodes to working set
     */

    if (nums.size == 1) return 0
    val debug = Array.fill(nums.length)(0)

    val visited = mu.Set.empty[Int]
    val ordering: Ordering[(Int, Int)] =
      Ordering.by[(Int, Int), Int](_._2).reverse
    val workingSet: mu.PriorityQueue[(Int, Int)] =
      mu.PriorityQueue.empty(ordering)

    var found: Option[Int] = None
    workingSet.enqueue((0, 0))

    while (workingSet.size > 0 && found.isEmpty) {
      val (curIdx, curJmp) = workingSet.dequeue()
      val nextJmp = nums(curIdx)
      val reachable = (1 to nextJmp).map(jmp => curIdx + jmp)
      reachable.foreach { idx =>
        {
          if (idx < nums.size && !visited.contains(idx)) {
            visited.add(idx)
            workingSet.enqueue((idx, curJmp + 1))
            debug(idx) = curJmp + 1
          }
        }
      }
      if (visited.contains(nums.length - 1)) {
        found = Some(curJmp + 1)
      }

      // println(curIdx)
      // println(Vector.from(nums))
      // println(Vector.from(debug))
      // println
    }

    found.getOrElse(-1)
  }

  @main
  def test(): Unit = {
    println(dikIter(Array(2, 3, 1, 1, 4)))
//    println(dikIter(Array(2, 3, 0, 1, 4)))
//    println(dikIter(Array(5, 2, 1, 2, 5, 2, 6, 8, 1, 9, 3, 5, 8, 0, 2)))
  }

}
