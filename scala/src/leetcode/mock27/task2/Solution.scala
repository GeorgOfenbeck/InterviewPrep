package leetcode.mock27.task2

import scala.collection.{mutable => mu}

class MyQueue() {

  var a = mu.Stack.empty[Int]
  var b = mu.Stack.empty[Int]

  def push(x: Int): Unit = {
    a.push(x)
  }

  def pop(): Int = {
    if (b.nonEmpty) {
      b.pop()
    } else {
      while (a.nonEmpty) {
        b.push(a.pop())
      }
      b.pop()
    }
  }

  def peek(): Int = {
    if (b.nonEmpty) {
      val res = b.pop()
      b.push(res)
      res
    } else {
      while (a.nonEmpty) {
        b.push(a.pop())
      }
      val res = b.pop()
      b.push(res)
      res
    }
  }

  def empty(): Boolean = {
    a.isEmpty && b.isEmpty
  }

}
