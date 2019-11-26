package scala.leetcode.p485

case class State(sofar: Int, cur: Int)
object Solution {
  def findMaxConsecutiveOnes(nums: Array[Int]): Int = {

    val state: State = nums.foldLeft(State(0,0)){
      (acc,ele) => {
        if (ele == 0)
          State(acc.sofar,0)
        else {
          val newcur = acc.cur + 1
          if (newcur > acc.sofar)
            State(newcur, newcur)
          else
            State(acc.sofar, newcur)
        }
      }
    }
    state.sofar
  }
}
