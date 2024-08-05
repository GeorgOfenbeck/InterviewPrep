package leetcode.p160

class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}

object Solution {
  def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
    val alength = getLength(headA)
    val blength = getLength(headB)

    var aptr = headA
    var bptr = headB
    if (alength > blength) {
      val diff = alength - blength
      for (i <- 0 until diff) {
        aptr = aptr.next
      }
    } else {
      val diff = blength - alength
      for (i <- 0 until diff) {
        bptr = bptr.next
      }
    }

    // now they are same size
    while (aptr != bptr) {
      aptr = aptr.next
      bptr = bptr.next
    }
    aptr

  }

  def getLength(head: ListNode): Int = {
    var length = 0
    var ptr = head
    while (ptr != null) {
      ptr = ptr.next
      length = length + 1
    }
    length
  }
}
