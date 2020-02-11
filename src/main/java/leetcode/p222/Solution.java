package leetcode.p222;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;

class Solution {

    public static void main(String [] args){
        Solution sol = new Solution();
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        TreeNode _6 = new TreeNode(6);
        TreeNode _7 = new TreeNode(7);

        _1.right = _3;
        _1.left = _2;
        _2.left = _4;
        _2.right = _5;
        _3.left = _6;
        _3.right = _7;
        //System.out.println(sol.nodeExits(sol.idx2Direction(6),_1));
        System.out.println(sol.countNodes(_1));
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int dleft = getDepthLeft(root);
        int dright = getDepthRight(root);

        if (dleft == dright) return (int)Math.pow(2,dright)-1;
        assert(dleft > dright); //not complete binary node

        return binarySearch((int)Math.pow(2,dleft-1),(int) Math.pow(2,dleft),root);
    }

    //inclusive / noninclusive
    int binarySearch(int lower, int upper, TreeNode root){
        if (lower +1 >= upper) return lower;
        int mid = (upper-lower)/2 + lower;
        if (nodeExits(idx2Direction(mid),root)){ // =<
            return binarySearch(mid,upper, root);
        } else{ //>
            return binarySearch(lower, mid, root);
        }
    }


    Iterator<Boolean> idx2Direction(int j){
        int i = j;
        LinkedList<Boolean> res = new LinkedList<>();
        while (i > 1){
            if (i % 2 == 0)
                res.addFirst(true);
            else
                res.addFirst(false);
            i = i / 2;
        }
        return res.iterator();
    }


    public boolean nodeExits(Iterator<Boolean> it, TreeNode root){
        //if (it.hasNext() && root == null) return false;
        if (it.hasNext()){
            if (root == null) return false;
            boolean direction = it.next();
            if (direction) //left == true
                return nodeExits(it,root.left);
            else
                return nodeExits(it,root.right);
        } else{
            boolean exits = root != null;
            return exits;
        }
    }

    public int getDepthRight(TreeNode root){
        TreeNode ptr = root;
        int deepth = 1;
        while (ptr.right != null){
            deepth ++;
            ptr = ptr.right;
        }
        return deepth;
    }
    public int getDepthLeft(TreeNode root){
        TreeNode ptr = root;
        int deepth = 1;
        while (ptr.left != null){
            deepth ++;
            ptr = ptr.left;
        }
        return deepth;
    }


    public int findRightMost(TreeNode root, int sofar){
        if (root.right != null){
            return findRightMost(root.right, sofar * 2 + 1);
        }
        if (root.left != null){
            return findRightMost(root.left, sofar * 2);
        }

        //both are null
        return sofar;

    }

}