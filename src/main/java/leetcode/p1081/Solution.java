package leetcode.p1081;


import java.util.*;



class Solution {


    public static void main (String[] args){
        String Input = "ecbacba";
        String Output =  "eacb";

        Solution sol = new Solution();
        System.out.println(sol.smallestSubsequence(Input));
    }
    public String smallestSubsequence(String text) {
        int[] count = new int[128];
        for (char c : text.toCharArray()) count[c]++;

        Deque<Character> stack = new ArrayDeque<>();

        // Whether char c is in stack
        boolean[] used = new boolean[128];

        for (char c : text.toCharArray()) {
            count[c]--;
            if (used[c]) continue;

            while (stack.size() > 0 && stack.peek() > c && count[stack.peek()] > 0) {
                used[stack.pop()] = false;
            }
            stack.push(c);
            used[c] = true;
        }

        StringBuilder ans = new StringBuilder();
        while (stack.size() > 0) ans.append(stack.removeLast());
        return ans.toString();
    }
}