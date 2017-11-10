package leetcode.p647;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countSubstrings("aaaaa"));

    }

    public String reverse(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public void findPalindrom(String s1, String s2, HashSet<String> res){
        assert(s1.length() == s2.length());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< s1.length(); i++){
            Character a = s1.charAt(i);
            Character b = s2.charAt(i);
            if (a == b)
                sb.append(a);
            else{
                if (sb.length() > 1) {
                    res.add(sb.toString());
                }
                sb.delete(0,sb.length());
            }
        }
        if (sb.length() > 1) {
            res.add(sb.toString());
        }
    }


    public int countSubstrings(String s) {

        String reverse = reverse(s);

        Integer tl = s.length()*2;

        //LinkedList<String> res = new LinkedList<>();
        HashSet<String> res = new HashSet<>();
        //take behind original
        for (int i = 1; i < s.length()-1; i++){
            String frag1 = s.substring(s.length()-1-i,s.length());
            String frag2 = reverse.substring(0,i+1);
            findPalindrom(frag1,frag2,res);
        }

        //same size
        findPalindrom(s,reverse,res);

        //take front original
        for (int i = 1; i < s.length()-1; i++){
            String frag1 = reverse.substring(s.length()-i-1,s.length());
            String frag2 = s.substring(0,i+1);
            findPalindrom(frag1,frag2,res);
        }

        Integer count = 0;

        count = res.stream().map(pal ->{
            Integer l = pal.length();
            if (l == 2)
                return 1;
            else{
                return 1;
            }
        }).mapToInt(i -> i).sum();



        return count + s.length();
    }
}
