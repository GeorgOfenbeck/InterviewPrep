package leetcode.p033;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] arr = {8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 4, 4, 4, 8};
        Solution sol = new Solution();
        int idx = sol.search(arr, 15);
        System.out.println(idx);
    }

    int search(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return -1;
        if (nums.length == 1)
            if (nums[0] == target) return 0;
            else return -1;


        if (nums[0] < nums[nums.length-1]){ //its not rotated
            return nt1(Arrays.binarySearch(nums, 0, nums.length , target));
        }
        int idx = findbreak(nums, nums[0], 0, nums.length);
        //System.out.println(idx);
        if (target == nums[0])
            return 0;
        else if (target < nums[0]) {
            return nt1(Arrays.binarySearch(nums, idx, nums.length , target));
        } else
            return nt1(Arrays.binarySearch(nums, 0, idx , target));
    }

    int nt1 (int num) {
        if (num < 0) return -1;
        else return num;
    }


    int findbreak(int[] nums, int smallerthen, int left, int right) {
        if (left > right)
            return left;
        int mid = left + (right - left) / 2;
        int cur = nums[mid];
        if (cur > smallerthen)
            return findbreak(nums, smallerthen, mid + 1, right);
        else {
            return findbreak(nums, cur, left, mid - 1);
        }
    }


}