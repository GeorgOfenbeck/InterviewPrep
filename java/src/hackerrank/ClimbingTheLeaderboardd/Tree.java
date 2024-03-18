package hackerrank.ClimbingTheLeaderboardd;

public abstract class Tree<K,V> {
    K key;
    V value;
    Tree<K,V> left;
    Tree<K,V> right;
    int count;

    abstract Tree<K,V> black();
    abstract Tree<K,V> red();


}
