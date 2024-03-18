package leetcode.p1048;


import java.util.*;
import java.math.BigInteger;

// used https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html as template
class RollingHash{

    private final static int R = 256;
    private final static BigInteger qBI = BigInteger.probablePrime(31, new Random());
    public final static int q = qBI.intValue();


    private static int getBasePowMod(int i){
        assert(basePowMod.size() >= i); //when we query i == size it will grow by one
        if (i == basePowMod.size()){
            BigInteger t = BigInteger.valueOf(R);
            Integer v = t.pow(i).mod(qBI).intValue();
            basePowMod.add(v);
            return basePowMod.get(i);
        } else {
            return basePowMod.get(i);
        }
    }

    static ArrayList<Integer> basePowMod = new ArrayList<>();



    private int sum;
    private String str;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RollingHash that = (RollingHash) o;
        return this.str.equals(that.str);
    }

    @Override
    public int hashCode() {
        return sum;
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    public RollingHash(String pat) {
        str = pat;
        sum = 0;
        //compute the hash
        for (int i =0; i < pat.length(); i++){
            int cur = pat.charAt(pat.length()-1-i);
            sum = (int)(((long)sum * R)%q + cur) % q;
        }
    }

    int subHash(int i){
        if (i > str.length()) return -1;
        else {
            return (sum - (str.charAt(i) * getBasePowMod(i))) % q;
        }
    }

}


class Solution {

    public static void main(String [] args){
        HashSet<String> test = new HashSet<>();
        String current = "abc";

        ArrayList<String> substrings = new ArrayList<>();
        for (int i = 0; i < current.length(); i++){
            //if (true) {

                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < current.length(); j++) {
                    if (i != j)
                        sb.append(current.charAt(j));
                }
                substrings.add(sb.toString());
        }

        for (String s: substrings){
            System.out.println(s);
            System.out.println(new RollingHash(s).hashCode());
        }
        System.out.println();



        RollingHash rh = new RollingHash(current);
        System.out.println(new RollingHash(current).hashCode());
        System.out.println(rh.hashCode());

        for (int i = 0; i< current.length();i++){
            System.out.println(rh.subHash(i));
        }



        String[] in = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        Solution sol = new Solution();
        int res = sol.longestStrChain(in);
        System.out.println(res);
    }



    TreeMap<Integer, HashSet<String>> sortByLength(String[] words) {
        TreeMap<Integer, HashSet<String>> treeMap = new TreeMap<>();
        for (String w : words) {
            int l = w.length();
            HashSet<String> sofar = treeMap.getOrDefault(l, new HashSet<>());
            sofar.add(w);
            treeMap.put(l,sofar);
        }
        return treeMap;
    }

    Boolean hasPrefix(HashSet<String> prefix, String current) {
        for (int i = 0; i < current.length(); i++){
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < current.length(); j++){
                if (i != j)
                    sb.append(current.charAt(j));
            }
            String forHash = sb.toString();
            if (prefix.contains(forHash))
                return true;
        }
        return false;
    }

    Integer getPreLen(RollingHash rh, HashMap<String,Integer> prefix, HashSet<Integer> prefixHash, String current) {
        int maxPreLen = 0;
        for (int i = 0; i < current.length(); i++){
            int subHash = rh.subHash(i);
            if (true) {
            //if (prefixHash.contains(subHash)) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < current.length(); j++) {
                    if (i != j)
                        sb.append(current.charAt(j));
                }
                String forHash = sb.toString();
                maxPreLen = Math.max(prefix.getOrDefault(forHash, 0), maxPreLen);
            }
        }
        return maxPreLen + 1;
    }

    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) return 0;
        TreeMap<Integer, HashSet<String>> wordsByLength = sortByLength(words);
        if (wordsByLength.size() == 1) return 1;

        //we have at least two sizes -> start bottom up
        int maxLen = 0;
        Iterator<Map.Entry<Integer, HashSet<String>>> iterator = wordsByLength.entrySet().iterator();
        HashMap<String,Integer> seen = new HashMap<>();
        HashSet<Integer> seenHashCode = new HashSet<>();
        while(iterator.hasNext()){
            Map.Entry<Integer, HashSet<String>> next = iterator.next();
            HashSet<String> curStrings = next.getValue();


            if (wordsByLength.containsKey(next.getKey()-1)) {
                for (String s : curStrings) {
                    RollingHash rh = new RollingHash(s);
                    int len = getPreLen(rh,seen, seenHashCode, s);
                    maxLen = Math.max(len, maxLen);
                    seen.put(s, len);
                    seenHashCode.add(rh.hashCode());
                }
            } else {
                for (String s : curStrings) {
                    int len = 1;
                    RollingHash rh = new RollingHash(s);
                    maxLen = Math.max(len, maxLen);
                    seen.put(s, len);
                    seenHashCode.add(rh.hashCode());
                }
            }

        }
        return maxLen;
        /*

        HashSet<String> candidates = wordsByLength.getOrDefault(2, new HashSet<>());
        HashSet<String> prefix = wordsByLength.getOrDefault(1, new HashSet<>());
        if (prefix.size() == 0) return 0;
        if (candidates.size() == 0) return 1;
        int size = 1;

        while (candidates.size() > 0 && prefix.size() > 0 ) {
            HashSet<String> newWorkingSet = new HashSet<>();

            for (String s : candidates) {
                if (hasPrefix(prefix, s))
                    newWorkingSet.add(s);
            }
            prefix = newWorkingSet;
            if (prefix.size() > 0)
                size ++;
            candidates = wordsByLength.getOrDefault(size + 1, new HashSet<>());
        }
        return size;*/
    }
}