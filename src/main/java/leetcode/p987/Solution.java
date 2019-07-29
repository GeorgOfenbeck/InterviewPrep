/*
package leetcode.p987;



import java.util.*;
 * Definition for a binary tree node.
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }



class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        TreeMap<Integer,TreeMap<Integer,List<Integer>>> tmap = new TreeMap<>();
        traverse(tmap,root,0,0);
        ArrayList<List<Integer>> llist = new ArrayList<List<Integer>>() ;

        for (int i = 0; i < tmap.size(); i++){
            tmap.forEach( (key,value) -> {
                ArrayList<Integer> innerlist = new ArrayList<>();
                value.forEach( (innerkey, innervalue) -> innervalue.forEach(val -> innerlist.add(val)));
                llist.add(innerlist);
            });
        }

        return llist;
    }


    private TreeMap<Integer,TreeMap<Integer,List<Integer>>> traverse(TreeMap<Integer,TreeMap<Integer,List<Integer>>> tmap, TreeNode tree, Integer x, Integer y){
        if (tree == null) return tmap;
        traverse(tmap,tree.left,x-1,y-1);
        TreeMap<Integer, List<Integer>> cur;
        if (tmap.containsKey(x)) {
             cur = tmap.get(x);
        } else {
            cur = new TreeMap<>();
            tmap.put(x,cur);
        }
        if (cur.containsKey(y)){
            List<Integer> list = cur.get(y);
            list.add(tree.val);
        } else
        {
            List<Integer> list = new ArrayList<>();
            list.add(tree.val);
            cur.put(y,list);
        }

        traverse(tmap,tree.right,x+1,y-1);
        return tmap;
    }


}*/