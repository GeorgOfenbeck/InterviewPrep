package leetcode.p328

class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

object Test{
    @main
    def main()={
        val l = ListNode(1,ListNode(2,ListNode(3,ListNode(4,ListNode(5,null)))))
        Solution.oddEvenList(l)
    }
}

object Solution {
  def oddEvenList(head: ListNode): ListNode = {

    var ptr = head
    val even: ListNode = ListNode(Int.MinValue, null)
    var evenptr: ListNode = even

    while (ptr != null) {
      if (ptr.next != null) {
        //
        val nextodd = ptr.next.next
        // put the even node in the evenptr list
        val tmp = ptr.next
        evenptr.next = tmp
        evenptr = tmp
        evenptr.next = null

        // check if the even node was the last in
        if (nextodd == null) {
          ptr.next = even.next
          ptr = null
        } else {
          ptr.next = nextodd
          ptr = nextodd
        }
      } else { // we are at a final odd node - just add the even nodes after
        ptr.next = even.next
        ptr = null
      }
    }
    head
  }
}
