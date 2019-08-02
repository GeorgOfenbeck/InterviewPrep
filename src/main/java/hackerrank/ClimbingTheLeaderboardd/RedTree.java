package hackerrank.ClimbingTheLeaderboardd;

public class RedTree<K,V> extends Tree<K,V>{
    RedTree(K k, V v, Tree<K, V> l, Tree<K,V> r){

    }

    @Override
    Tree<K, V> black() {
        return new BlackTree<>(key,value,left,right);
    }

    @Override
    Tree<K, V> red() {
        return this;
    }
}
