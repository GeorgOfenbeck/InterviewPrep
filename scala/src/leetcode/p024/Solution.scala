package leetcode.p024

/** Definition for singly-linked list. class ListNode(_x: Int = 0, _next:
  * ListNode = null) { var next: ListNode = _next var x: Int = _x }
  */
object Solution {
  def swapPairs(head: ListNode): ListNode = {
    if (head == null) return head

    val headptr = ListNode(0, head)
    var prev = headptr

    while (prev.next != null && prev.next.next != null) {
      val a = prev.next
      val b = a.next
      val c = b.next // might be null

      prev.next = b
      b.next = a
      a.next = c
      prev = a
    }
    headptr.next
  }

}
