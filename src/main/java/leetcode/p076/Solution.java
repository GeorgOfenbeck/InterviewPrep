package leetcode.p076;
import java.util.*;


public class Solution {

    public static void main(String [] args){
        String S = "ADOBECODEBANC";
        String T = "ABC";
        Solution sol = new Solution();
        System.out.println(sol.minWindow(S,T));
    }

    class FingerPrintCheck{
        HashMap<Character, Integer> target;
        HashMap<Character, Integer> running;
        HashSet<Character> check;

        FingerPrintCheck(HashMap<Character, Integer> target){
            this.target = target;
            running = new HashMap<>();
            running.putAll(target);
            check = new HashSet<>();
            check.addAll(target.keySet());
        }

        boolean addChar(Character c){
            if (target.containsKey(c)){
                int nv = running.get(c) - 1;
                running.put(c,nv);
                if (nv <= 0){
                    check.remove(c);
                }
            }
            return check.isEmpty();
        }

        boolean removeChar(Character c){
            if (target.containsKey(c)){
                int nv = running.get(c) + 1;
                running.put(c,nv);
                if (nv > 0){
                    check.add(c);
                }
            }
            return check.isEmpty();
        }
    }

    public HashMap<Character, Integer> getFingerPrint(String s){
        HashMap<Character, Integer> res = new HashMap<>();
        for (Character c : s.toCharArray()){
            if (res.containsKey(c))
                res.put(c,res.get(c) + 1);
            else
                res.put(c, 1);
        }
        return res;
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int minsize = Integer.MAX_VALUE;
        int resi = 0, resj = 0;
        FingerPrintCheck fingerPrintCheck = new FingerPrintCheck(getFingerPrint(t));
        int j = 0;
        int size;
        for (int i = 0; i < s.length(); i++){
            boolean match = fingerPrintCheck.addChar(s.charAt(i));
            if (match){
                size =  i - j;
                if (size < minsize)
                {
                    resi = i;
                    resj = j;
                    minsize = size;
                }
                while (fingerPrintCheck.removeChar(s.charAt(j))){
                    j ++;
                    size =  i - j;
                    if (size < minsize)
                    {
                        resi = i;
                        resj = j;
                        minsize = size;
                    }
                }
                //the last one made it false - therefore add it again
                fingerPrintCheck.addChar(s.charAt(j));
            }
        }
        if (minsize < Integer.MAX_VALUE)
        {
            return s.substring(resj,resi+1);
        } else
            return "";

    }
}