/*
package scala.leetcode.p019

import scala.leetcode.p019.ListNode
object Solution {
    def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
       val length = getLength(head)

       //5 - 2 = 3
       var remove = length - n //
        
         if (remove == 0) {
              return head.next
         }
       var current: ListNode = head
       while (remove > 1) {
           current = current.next
           remove -= 1
       }
       
       current.next = current.next.next
       head
    }

    def getLength(head: ListNode): Int = {
        var length = 0
        var current = head
        while (current != null) {
            length += 1
            current = current.next
        }
        length
    }
} */