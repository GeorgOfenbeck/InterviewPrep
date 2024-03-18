package leetcode.p1038;


 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode(int x) {
         val = x;
     }
 }
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Helper{
    TreeNode root;
    int treesum;

    Helper(TreeNode root, int sum){
        this.root = root;
        this.treesum = sum;
    }
}

class Solution {

    public static void main(String args[]){
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(4);
        TreeNode one = new TreeNode(1);

        root.left = left;
        root.right = right;
        left.left = one;
        TreeNode res = sol.bstToGst(root);
    }


    public TreeNode bstToGst(TreeNode root) {
        Helper hlp =  recurse(root,0);
        return hlp.root;

    }

    Helper recurse(TreeNode root, int sumright){
        if (root == null) return new Helper(null,0);



        if (root.right == null && root.left == null){
            return new Helper(new TreeNode(root.val + sumright),root.val + sumright);
        } else {
            if (root.right == null){
                Helper lefthelper = recurse(root.left, sumright + root.val);
                TreeNode copy = new TreeNode(root.val + sumright);
                copy.left = lefthelper.root;
                return new Helper(copy, copy.val + lefthelper.root.val);
            } else {
                if (root.left == null) {
                    Helper righthelper = recurse(root.right, sumright);
                    TreeNode copy = new TreeNode(root.val + righthelper.treesum);
                    copy.right = righthelper.root;
                    return new Helper(copy, copy.val);
                } else {
                    Helper righthelper = recurse(root.right, sumright);
                    TreeNode copy = new TreeNode(root.val + righthelper.treesum);
                    copy.right = righthelper.root;
                    Helper lefthelper = recurse(root.left, righthelper.treesum + root.val);
                    copy.left = lefthelper.root;
                    return new Helper(copy,lefthelper.treesum);
                }

            }
        }

    }
}