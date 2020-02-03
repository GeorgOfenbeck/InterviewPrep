package scala.leetcode.p973

import scala.collection.{SortedMap, mutable}


object Blub extends App{

  val v = Array(1,2,3,4,5)

  /*
  for (int i = 0; i < v.size; i++){
    v(i) = v(i)*2;
  */

  for (i <- 0 until v.length/2){
    //v(i) = v(i) *2;
    println("...")
  }

  val res = v.map(x => x*2)

  val totalres = res.reduce( (a,b) => a + b)

  case class Signal(s: String)
  def remainingCharsCount():Int = 15

  val res1 = if (remainingCharsCount() >= 15) Signal("green")
  else if (remainingCharsCount() < 0) Signal("red")
  else Signal("orange")

  val res2 = Signal {
    val remaining = remainingCharsCount()
    if (remaining >= 15) {
      "green"
    } else if (remaining >= 0) {
      "orange"
    } else {
      "red"
    }
  }

  println(res1)
  println(res2)

}


object Solution {

  System.nanoTime()

  def kClosest(points: Array[Array[Int]], K: Int): Array[Array[Int]] = {
    val res = points.map(point => {
      val x = point(0)
      val y = point(1)
      val distance = Math.sqrt(x*x + y*y)
      distance -> point
    })

    implicit val ord:Ordering[Array[(Double,Array[(Int)])]] = ???
    val pq = mutable.PriorityQueue(res)
    pq.drop(K-1)
    ???
  }
}

