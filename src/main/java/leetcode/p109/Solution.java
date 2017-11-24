package leetcode.p109;

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

  public class ListNode {
      int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }

  public TreeNode sortedListToBST(ListNode head) {
    ArrayList<Integer> al = new ArrayList();

    ListNode ptr = head;
    while (ptr != null){
      al.add(ptr.val);
    }
    return createTree(al,0,al.size()-1);

  }

  //only call with min 1 ele
  TreeNode createTree(ArrayList<Integer> al, Integer start, Integer end){
    if (end > end-1) return null;
    if (start == end){
      return new TreeNode(al.get(start));
    }
    else{
      // 123, 12 (2), 1234
      Integer half = al.size()/2;
      TreeNode left = createTree(al,start, half-1);
      TreeNode right = createTree(al,half+1, end);
      TreeNode mid = new TreeNode(half);
      mid.left = left;
      mid.right = right;
      return mid;
    }

  }



}
