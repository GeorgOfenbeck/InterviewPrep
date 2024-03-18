package leetcode.p583;



import java.math.BigInteger;
import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args) {

    }


    public Set<Character> diff (Set<Character> a, Set<Character> b){
        Set<Character> c = new HashSet<Character>();
        a.forEach(ac -> {
            if(b.contains(ac))
                c.add(ac);
        });
        return c;
    }

    public int minDistance(String word1, String word2) {
        Set<Character> a = new HashSet<Character>();
        Set<Character> b = new HashSet<>(word2.length());

        word1.chars().forEach(c -> a.add((char) c));
        word2.chars().forEach(c -> b.add((char) c));

        LinkedList<Character> r1 = new LinkedList<>();
        LinkedList<Character> r2 = new LinkedList<>();

        Set<Character> c = diff(a,b);

        return 0;
    }
}