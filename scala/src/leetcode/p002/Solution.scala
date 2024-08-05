package leetcode.p002

class ListNode(_x: BigInt = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: BigInt = _x
}

/** Definition for singly-linked list. class ListNode(_x: BigInt = 0, _next:
  * ListNode = null) { var next: ListNode = _next var x: BigInt = _x }
  */
object Solution {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    number2List(list2Number(l1) + list2Number(l2))
  }

  def list2Number(l: ListNode): BigInt = {
    var i: BigInt = 1
    var sum: BigInt = 0
    var ptr: ListNode = l
    while (ptr != null) {
      val digit = ptr.x
      ptr = ptr.next
      sum = sum + digit * i
      i = i * 10
    }
    sum
  }

  def number2List(l: BigInt): ListNode = {
    if (l == 0)
      return ListNode(0, null)
    else {
      var remain = l
      var forward = List.empty[BigInt]
      while (remain > 0) {
        val digit = remain % 10
        forward = digit +: forward
        remain = remain / 10
      }
      var ptr: ListNode = null
      for (item <- forward) {
        ptr = ListNode(item.toInt, ptr)
      }
      ptr
    }
  }
}
