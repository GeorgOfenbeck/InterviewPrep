package hackerrank.ClimbingTheLeaderboardd;

import java.util.*;

public class RedBlackTree<K,V> {

    Comparator<K> comperator;

    static Boolean isEmpty(Tree tree) {
        return tree == null;
    }

    /*
    @tailrec
    def lookup[A, B](tree: Tree[A, B], x: A)(implicit ordering: Ordering[A]): Tree[A, B] = if (tree eq null) null else {
        val cmp = ordering.compare(x, tree.key)
        if (cmp < 0) lookup(tree.left, x)
        else if (cmp > 0) lookup(tree.right, x)
        else tree
    }*/

    Tree<K,V> lookup(Tree<K,V> tree, K x) {
        if (tree == null) return null;
        else {
            int cmp = comperator.compare(x, tree.key);
            if (cmp < 0) return lookup(tree.left, x);
            else if (cmp > 0) return lookup(tree.right, x);
            else return tree;
        }
    }

    int count (Tree<K,V> tree) {
        if (tree == null) return 0;
        else return tree.count;
    }

    Boolean isBlack( Tree<K,V> tree){
      return (tree == null) || isBlackTree(tree);
    }

    private Boolean isRedTree(Tree<K,V> tree) {
        return (tree instanceof RedTree);
    }

    private Boolean isBlackTree(Tree<K,V> tree) {
        return (tree instanceof BlackTree);
    }

    private Tree<K,V> blacken(Tree<K,V> tree) {
        if (tree == null) return null;
        else return tree.black();
    }

    private Tree<K,V> mkTree(Boolean isBlack, K k, V v, Tree<K, V> l, Tree<K,V> r) {
        if (isBlack) return new BlackTree(k,v,l,r);
        else return new RedTree(k,v,l,r);
    }
    /*
    private[this] def balanceLeft[A, B, B1 >: B](isBlack: Boolean, z: A, zv: B, l: Tree[A, B1], d: Tree[A, B1]): Tree[A, B1] = {
        if (isRedTree(l) && isRedTree(l.left))
            RedTree(l.key, l.value, BlackTree(l.left.key, l.left.value, l.left.left, l.left.right), BlackTree(z, zv, l.right, d))
        else if (isRedTree(l) && isRedTree(l.right))
            RedTree(l.right.key, l.right.value, BlackTree(l.key, l.value, l.left, l.right.left), BlackTree(z, zv, l.right.right, d))
        else
            mkTree(isBlack, z, zv, l, d)
    }*/

    Tree<K,V> balanceLeft(Boolean isBlack, K z, V zv, Tree<K,V> l, Tree<K,V> d){
        if (isRedTree(l) && isRedTree(l.left)) {
            return new RedTree<>(l.key, l.value, new BlackTree<>(l.left.key, l.left.value, l.left.left, l.left.right),new BlackTree<>(z, zv, l.right, d));
        } else if (isRedTree(l) && isRedTree(l.right)){
            return new RedTree(l.right.key, l.right.value, new BlackTree(l.key, l.value, l.left, l.right.left), new BlackTree(z, zv, l.right.right, d));
        } else return mkTree(isBlack, z, zv, l, d);
    }

}
