package leetcode.p109;

import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
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

    //[-10,-3,0,5,9],

    public static void main(String[] args) {
        ListNode nine = new ListNode(9);
        ListNode five = new ListNode(5);
        five.next = nine;
        ListNode zero = new ListNode(0);
        zero.next = five;
        ListNode mthree = new ListNode(-3);
        mthree.next = zero;
        ListNode mten = new ListNode(-10);
        mten.next = mthree;

        Solution sol = new Solution();
        sol.sortedListToBST(mten);

    }
    /*




     */
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


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (false) {
            ArrayList<Integer> arr = new ArrayList();
            ListNode ptr;
            for (ptr = head; ptr != null; ptr = ptr.next) {
                arr.add(ptr.val);
            }
            return treeify(arr, 0, arr.size() - 1);
        } else {
            ListNode ptr;
            int i = 0;
            for (ptr = head; ptr != null; ptr = ptr.next) {
                i++;
            }
            Ret ret = treeify_inorder(head,i);
            return ret.tree;
        }
    }


    private class Ret {
        TreeNode tree;
        ListNode list;
        int nrnodes;

        Ret(TreeNode tree,
            ListNode list,
            int nrnodes) {
            this.tree = tree;
            this.list = list;
            this.nrnodes = nrnodes;
        }
    }

    public Ret treeify_inorder(ListNode list, int nrNodes) {
            if (nrNodes == 0) {
                return new Ret(null,list,nrNodes);
            } else {
                if (nrNodes == 1){
                    TreeNode cur = new TreeNode(list.val);
                    return new Ret(cur,list.next,nrNodes -1);
                } else {
                    int leftsize = (nrNodes - 1)/2;
                    int rightsize = (nrNodes -1) - leftsize;

                    Ret leftret = treeify_inorder(list,leftsize);
                    TreeNode cur = new TreeNode(leftret.list.val);
                    Ret rightret = treeify_inorder(leftret.list.next,rightsize);
                    cur.left = leftret.tree;
                    cur.right = rightret.tree;
                    return new Ret(cur,rightret.list,0);
                }
            }

    }


    public TreeNode treeify(ArrayList<Integer> arr, int lower, int upper) {
        if (lower == upper) {
            TreeNode res = new TreeNode(arr.get(lower));
            return res;
        } else {
            if (upper - lower == 1) {
                TreeNode leftNode = new TreeNode(arr.get(lower));
                TreeNode rootNode = new TreeNode(arr.get(upper));
                rootNode.left = leftNode;
                return rootNode;
            } else {
                //upper - lower >= 2


                //0,1,2
                //diff = 2
                //
                int diff = upper - lower;
                int root = lower + diff / 2;
                TreeNode rootNode = new TreeNode(arr.get(root));

                TreeNode leftNode = treeify(arr, lower, root - 1);
                TreeNode rightNode = treeify(arr, root + 1, upper);
                rootNode.left = leftNode;
                rootNode.right = rightNode;
                return rootNode;
            }

        }


    }
}
