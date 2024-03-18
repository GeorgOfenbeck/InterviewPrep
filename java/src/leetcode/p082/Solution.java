package leetcode.p082;
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode newhead = head;
        ListNode cur = head;
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        cur = dummy;
        int curval = head.val;

        while (cur.next != null){
            if (hasDuplicate(cur.next))
                deleteDups(cur);
            else
                cur = cur.next;
        }
        return dummy.next;
    }

    boolean hasDuplicate(ListNode head){
        if (head.next == null) return false;
        if (head.next.val == head.val) return true;
        else return false;
    }

    void deleteDups(ListNode onebefore){
        int dup = onebefore.next.val;
        while (onebefore.next != null && onebefore.next.val == dup)
            onebefore.next = onebefore.next.next;
    }

}
