package leetcode.mock21.task2

object Solution {
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {

    var resultlist: ListNode = null
    var carry = 0

    var l1p = l1
    var l2p = l2

    // one can be at end
    while (l1p != null || l2p != null) {
      val l1o: Option[ListNode] = if (l1p == null) None else Some(l1p)
      val l2o: Option[ListNode] = if (l2p == null) None else Some(l2p)
      val l1v = l1o.map(x => x.x)
      val l2v = l2o.map(x => x.x)
      val res = l1v.getOrElse(0) + l2v.getOrElse(0) + carry

      val digit = if (res >= 10) {
        carry = 1
        res % 10
      } else {
        carry = 0
        res
      }

      resultlist = ListNode(digit, resultlist)
      l1p = l1o.map(x => x.next).getOrElse(null)
      l2p = l2o.map(x => x.next).getOrElse(null)
    }

    if (carry == 1) {
      resultlist = ListNode(1, resultlist)
    }
    var reverse: ListNode = null

    var ptr = resultlist
    while (ptr != null) {
      reverse = ListNode(ptr.x, reverse)
      ptr = ptr.next
    }
    reverse

  }

}
