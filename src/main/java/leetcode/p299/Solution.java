package leetcode.p299;


import java.util.*;


class Solution {

    public static void main (String [] args){
        Solution sol = new Solution();
        sol.getHint("1123",
                "0111");
    }

    public String getHint(String secret, String guess) {
        if (secret == null || guess == null) return null;
        if (secret.length() != guess.length()) return null;

        HashMap<Character, Integer> perfect = new HashMap<>();
        HashMap<Character, Integer> signatureSecret = new HashMap<>();
        HashMap<Character, Integer> signatureGuess = new HashMap<>();

        for (int i = 0; i < secret.length(); i ++){
            Character guessFocus = guess.charAt(i);
            Character secretFocus = secret.charAt(i);

            if (guessFocus.equals(secretFocus)){
                perfect.put(guessFocus,perfect.getOrDefault(guessFocus,0) + 1);
            }
            signatureGuess.put(guessFocus,signatureGuess.getOrDefault(guessFocus,0) + 1);
            signatureSecret.put(secretFocus,signatureSecret.getOrDefault(secretFocus,0) + 1);
        }

        int nrPerfects = 0;
        int nrShuffeld = 0;

        for( Map.Entry<Character,Integer> entry : signatureSecret.entrySet()){
            Character k = entry.getKey();
            Integer v = entry.getValue();
            int curPerfects = 0;
            if (perfect.containsKey(k)){
                curPerfects = perfect.get(k);
            }

            int curGuessed = signatureGuess.getOrDefault(k,0);
            int remain = v - curPerfects; //number of k's not correctly guessed

            nrPerfects = nrPerfects + curPerfects;
            nrShuffeld = nrShuffeld + Math.min(remain,curGuessed - curPerfects);
        }




        return nrPerfects + "A" + nrShuffeld + "B";

    }
}