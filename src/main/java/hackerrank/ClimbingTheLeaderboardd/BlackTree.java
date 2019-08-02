package hackerrank.ClimbingTheLeaderboardd;

public class BlackTree<K,V> extends Tree<K,V>{



    BlackTree(K k, V v, Tree<K, V> l, Tree<K,V> r){

    }

    @Override
    Tree<K, V> black() {
        return this;
    }

    @Override
    Tree<K, V> red() {
        return new RedTree<>(key,value,left,right);
    }
}
