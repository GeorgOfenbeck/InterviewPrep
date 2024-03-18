package leetcode.p410;


import java.util.Arrays;

class Solution {

    public static void main (String [] args){
        int [] nums = {7,2,5,10,8};
        //int [] nums = {1,2147483647};
        Solution sol = new Solution();
        System.out.println(sol.splitArray(nums,3));
    }


    public int splitArray(int[] nums, int m) {

        int minVal = Integer.MIN_VALUE, maxVal = 0;
        for (int num : nums) {
            minVal = Math.max(minVal, num);
            maxVal += num;
        }

        while (minVal < maxVal) {
            int mid = minVal + (maxVal - minVal) / 2;
            if (canSplit(mid, nums, m)) {
                maxVal = mid;
            } else {
                minVal = mid + 1;
            }
        }

        return minVal;
    }

    private boolean canSplit(int upperBoundSubarraySum, int[] nums, int m) {

        int curSubarraySum = 0, cntSubarray = 1;
        for (int num : nums) {
            curSubarraySum += num;
            if (curSubarraySum > upperBoundSubarraySum) {
                cntSubarray++;
                curSubarraySum = num;
                if (cntSubarray > m) {
                    return false;
                }
            }
        }

        return true;
    }
    /*
    public int splitArray(int[] nums, int m) {
        if (m == 0 ) return 0;
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        long [] prefixsum = new long[nums.length];

        prefixsum[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            prefixsum[i] = prefixsum[i-1] + nums[i];
        }
        if (m == 1) return (int) prefixsum[nums.length-1];

        return splitArray(nums,prefixsum,0,m);

    }

    public int splitArray(int [] nums, long [] prefix, int start, int m){
        long reduce = 0;
        if (start > 0){
            reduce = prefix[start-1];
        }
        if (m == 1){
            return (int) (prefix[prefix.length-1] - reduce);
        }

        long avgsum = (prefix[prefix.length-1] - reduce) / m;
        long avgele = (prefix[prefix.length-1] - reduce) / (prefix.length - start);

        int j = start;
        for (int i = start; prefix[i] - reduce <= avgsum && i < prefix.length-m+1; i++){
            j = i;
        }
        //we are still smaller
        long asIs = Math.max(prefix[j]-reduce, splitArray(nums,prefix,j + 1,m-1));
        long takelast = Long.MAX_VALUE;
        if (nums.length - 1  - j >= m-1)
            takelast = Math.max(prefix[j+1] - reduce,splitArray(nums,prefix,j+2,m-1));


        return (int) Math.min(takelast,asIs);

    }*/

}