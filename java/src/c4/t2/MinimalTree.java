package c4.t2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinimalTree<T> {

    public class Node<T>{
        Node<T> left;
        Node<T> right;
        T value;
    }

    public Node<T> createTree(int size){
        return new Node<T>();
    }

    public Node<T> toTree(List<T> inputarray){
        int size = inputarray.size();
        Node<T> tree = createTree(size);
        iniTree(tree,inputarray);
        return tree;
    }

    public List<T> iniTree(Node<T> tree, List<T> values){
        if (tree.left != null)
            values = iniTree(tree.left, values);

        tree.value = values.get(0);
        if (tree.right != null)
            values = iniTree(tree.right, values.subList(1,values.size()));
        return values;
    }


    public void depthLists(Node<T> tree, Integer deepth, List<List<T>> lists){
        if (lists.size() < deepth-1)
            lists.add(deepth,new ArrayList<>());
        List<T> curlist = lists.get(deepth);
        curlist.add(tree.value);
        if (tree.left != null)
        depthLists(tree.left,deepth+1,lists);
        if (tree.right != null)
        depthLists(tree.right,deepth+1,lists);
    }

    public List<List<T>> depthLists(Node<T> tree){
        List<List<T>> lists= new ArrayList<>();
        depthLists(tree,0,lists);
        return lists;
    }

    public int depth(Node<T> tree){
     int dleft = depth(tree.left);
     if (dleft == -1)
         return -1;
     int dright = depth(tree.right);
     if (Math.abs(dleft - dright) > 1)
         return -1;
     int max = 0;
     if (dleft > dright)
         max = dleft;
     else max = dright;
     return max + 1;
    }
}
