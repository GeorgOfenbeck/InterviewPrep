package leetcode.p095;

/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    public static void main (String[] args){
        Solution sol = new Solution();
        sol.generateTrees(3);
        System.out.println(sol);
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            List<TreeNode> trees = generateTrees(1, n, i);
            for (TreeNode j : trees) {
                result.add(j);
            }
        }
        return result;
    }

    public List<TreeNode> generateTrees(int start, int stop, int root) {
        if (start == stop) {
            List<TreeNode> list = new LinkedList<>();
            TreeNode leaf = new TreeNode(root);
            list.add(leaf);
            return list;
        } else {
            List<TreeNode> left = new LinkedList<>();
            for (int i = start; i < root; i++) {
                List<TreeNode> leftlist = generateTrees(start, root - 1, i);
                for (TreeNode j : leftlist) {
                    left.add(j);
                }
            }
            List<TreeNode> right = new LinkedList<>();
            for (int i = root + 1; i <= stop; i++) {
                List<TreeNode> leftlist = generateTrees(root + 1, stop, i);
                for (TreeNode j : leftlist) {
                    right.add(j);
                }
            }

            List<TreeNode> result = new LinkedList<>();

            if (left.isEmpty()) {
                for (TreeNode r : right) {
                    TreeNode rootnode = new TreeNode(root);
                    rootnode.right = r;
                    result.add(rootnode);
                }
            }
            if (right.isEmpty()) {
                for (TreeNode r : right) {
                    TreeNode rootnode = new TreeNode(root);
                    rootnode.right = r;
                    result.add(rootnode);
                }
            }

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode rootnode = new TreeNode(root);
                    rootnode.left = l;
                    rootnode.right = r;
                    result.add(rootnode);
                }
            }
            return result;

        }
    }
}

