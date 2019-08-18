package leetcode.p069;


class Solution {

    public static void main (String [] args){
        Solution sol = new Solution();
        int res = sol.mySqrt(8);
        System.out.println(res);

    }


    public int mySqrt(int x) {

        if (x <= 0) return 0;
        else {
            if (x == 1) return 1;
            else
                return recurse(0,x/2,x);
        }
    }

    int recurse(int left, int right, int target){
        if (left == right)
            return left;
        if (right - left == 1)
            if (right*right > target)
                return left;
            else
                return right;
        long middle = left + (right - left)/2;
        long square = middle * middle;
        long squarep1 = (middle+1) * (middle+1);
        if (square <= target && squarep1 > target) return (int)middle;
        else {
            if (square < target)
                return recurse((int)(middle+1),right,target);
            else
                return recurse(left,(int)(middle-1),target);
        }
    }
}
