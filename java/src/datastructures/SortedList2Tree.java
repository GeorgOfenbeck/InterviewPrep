package datastructures;

import java.util.PriorityQueue;
import java.util.Queue;



public class SortedList2Tree {

    static int log(int x, int base)
    {
        return (int) (Math.log(x) / Math.log(base));
    }


    //return fitting tree height
    public static int size2TreeSeize(int size) {
        //2^h -1 <= size
        int nodes = 0;
        int i = 0;
        int height = 0;
        for (; nodes < size; i++) {
            height = i;
            nodes = (1 << i) - 1;
        }
        return height;
    }




    public static Tree fillTreeBF(Tree parent, int height, int[] arr, int start, int end, int count){
        if (start >= end || count <= 0)
            return null;
        if (height == 1) {
            return new Tree(null, null, parent, arr[start]);
        } else {
            int elements = end - start - 1;
            int perside = (1 << (height-1)) -1;

            int leftsidecount = elements/2;
            int rightsidecount = elements - leftsidecount;


            int rootvalue = arr[start+perside];
            Tree root = new Tree(null,null,parent,rootvalue);
            Tree leftside = fillTreeBF(root,height -1,arr,start, end, leftsidecount);
            Tree rightside = fillTreeBF(root, height -1,arr,start + perside + 1,end, rightsidecount);
            root.left = leftside;
            root.right = rightside;
            return root;
        }
    }


    public static Tree fillTree(Tree parent, int height, int[] arr, int start, int end){
        if (start >= end)
            return null;
        if (height == 1) {
            return new Tree(null, null, parent, arr[start]);
        } else {
            int perside = (1 << (height-1)) -1;
            int rootvalue = arr[start+perside];
            Tree root = new Tree(null,null,parent,rootvalue);
            Tree leftside = fillTree(root,height -1,arr,start, end);
            Tree rightside = fillTree(root, height -1,arr,start + perside + 1,end);
            root.left = leftside;
            root.right = rightside;
            return root;
        }
    }

    public static void printTree(Tree tree){
        if (tree.left != null) printTree(tree.left);
        if (tree.right != null) printTree(tree.right);
        String left = tree.left== null ? "null" : Integer.toString(tree.left.value);
        String right = tree.right== null ? "null" : Integer.toString(tree.right.value);
        System.out.println("Value:" + tree.value + " left child: " + left + " right child: " + right);
    }

    public static void inOrder(Tree tree){
        if (tree.left != null) inOrder(tree.left);
        System.out.println(tree.value);
        if (tree.right != null) inOrder(tree.right);
    }

    public static void breathFirst(Tree tree){
        if (tree.left != null) breathFirst(tree.left);
        if (tree.right != null) breathFirst(tree.right);
        System.out.println(tree.value);
    }

    public static void breathFirstIterative(Tree tree){

        PriorityQueue<Tree> queue = new PriorityQueue<>();

        queue.add(tree);

        while (!queue.isEmpty()) {
            Tree next = queue.poll();
            if (tree.left != null) breathFirst(tree.left);
            if (tree.right != null) breathFirst(tree.right);
        }

        if (tree.left != null) breathFirst(tree.left);
        if (tree.right != null) breathFirst(tree.right);
        System.out.println(tree.value);
    }




     public static Tree sortedArray2BalancedTree(int[] arr){
         if (arr == null) return null;
         if (arr.length == 0) return null;

         int height = size2TreeSeize(arr.length);

         return fillTree(null, height, arr, 0, arr.length);
     }


    public static void main( String args[]){

        int[] arr = {2,5,6,8,9,10,12}; //sorted asc

        Tree tree = sortedArray2BalancedTree(arr);
        printTree(tree);
        inOrder(tree);
        System.out.println("----------------------");
        breathFirst(tree);



    }
}
