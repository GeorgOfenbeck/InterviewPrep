package elemintprep.binary911;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Definition for a binary tree node. */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {

    public static void main(String[] args) {
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        TreeNode _6 = new TreeNode(6);
        TreeNode _7 = new TreeNode(7);

        _1.left = _2;
        _1.right = _3;
        _2.parent = _1;
        _2.left = _4;
        _2.right = _5;
        _4.parent = _2;
        _5.parent = _2;
        _3.parent = _1;
        _3.left = _6;
        _6.parent = _3;
        _3.right = _7;
        _7.parent = _3;

        Solution sol = new Solution();

        List<Integer> recurse = sol.traverseR(new ArrayList<>(), _1);
        List<Integer> imp = sol.traverseI(new ArrayList<>(), _1);
        List<Integer> o1 = sol.traverseO1(new ArrayList<>(), _1);


        recurse.forEach(x -> System.out.print(x + " "));
        System.out.println("\n---");
        imp.forEach(x -> System.out.print(x + " "));
        System.out.println("\n---");
        o1.forEach(x -> System.out.print(x + " "));
        System.out.println("\n---");

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return traverseI(list, root);
    }

    public TreeNode goLeftDown(TreeNode root) {
        if (root == null) return null;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode goRight(TreeNode root) {
        if (root == null) return null;
        if (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public TreeNode goLeftMostParent(TreeNode root) {
        if (root == null) return null;
        if (root.parent == null) return null;
        TreeNode child;
        do{
            child = root;
            root = root.parent;
        }
        while (root.parent != null && root.parent.left != root);

        return root;
    }


    public List<Integer> traverseO1(List<Integer> list, TreeNode root) {

        TreeNode cur = root;

        do {
            TreeNode left = goLeftDown(cur);
            if (left == cur) {
                //we dont have a left, so we didn't move
                list.add(cur.val);
            } else {
                //we moved
                list.add(left.val);

                if (left.right != null) //we are all the way left - but have a right child (no left)
                { }
                else {
                    //since we moved we must have a parent
                    left = left.parent;
                    list.add(left.val);
                }
            }
            TreeNode right = goRight(left);
            if (right == left) {//we reached the rightmost of this subtree
                TreeNode subroot = goLeftMostParent(left);
                if (subroot.parent != null) { //we are not back at the root
                    cur = subroot.parent;
                    list.add(cur.val);
                    right = goRight(cur);
                } else
                    right = subroot; //we are at root
            }
            cur = right;
        } while (cur.parent != null);
        return list;
    }

    public List<Integer> traverseR(List<Integer> list, TreeNode root) {
        if (root == null) {
            return list;
        } else {
            List<Integer> left = traverseR(list, root.left);
            left.add(root.val);
            List<Integer> right = traverseR(list, root.right);
            return right;
        }
    }


    public List<Integer> traverseI(List<Integer> list, TreeNode root) {

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) { //go left
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
}