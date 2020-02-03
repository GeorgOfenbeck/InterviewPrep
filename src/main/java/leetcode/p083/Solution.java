package leetcode.p083;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode ptr = head;
        int cur = ptr.val;
        while (ptr.next != null){
            if ( cur == ptr.next.val)
            {
                ptr.next = ptr.next.next;
            } else {
                ptr = ptr.next;
                cur = ptr.val;
            }
        }
        return head;
    }
}
