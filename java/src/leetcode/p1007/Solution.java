package leetcode.p1007;

public class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || B == null) return -1;
        if (A.length != B.length) return -1;
        if (A.length == 0) return -1;

        int[] aCount = new int[6]; //top row
        int[] bCount = new int[6]; //bottom row
        int[] dCount = new int[6]; //doubles (rotate useless)

        for (int i = 0; i < A.length; i++){
            aCount[A[i]]++;
            bCount[B[i]]++;
            if (A[i] == B[i]){
                dCount[A[i]] ++;
            }
        }

        int nrSwaps = -1;

        for (int i = 0; i < aCount.length; i++){
            if (aCount[i] + bCount[i] - dCount[i] == A.length){
                //#tops + #bottoms - doubles (still counting one) == length -> we can build a solution
                if (aCount[i] > bCount[i])
                    nrSwaps = Math.max(bCount[i]-dCount[i],nrSwaps);
                else
                    nrSwaps = Math.max(aCount[i]-dCount[i],nrSwaps);
            }
        }
        return nrSwaps;
    }
}
