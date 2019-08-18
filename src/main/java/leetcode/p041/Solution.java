package leetcode.p041;

import java.util.PriorityQueue;

public class Solution {

    public static void main (String [] args){
        int[] input = {7,8,9,11,12};
        Solution sol = new Solution();
        int res = sol.firstMissingPositive(input);
        System.out.println(res);
    }

    public int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }

    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

