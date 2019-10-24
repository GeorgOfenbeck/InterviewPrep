package leetcode.p035;

import java.util.*;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int ret = Arrays.binarySearch(nums, target);
        if (ret < 0)
            return -1 * (ret + 1); //<tt>(-insertion point) - 1)<
        else
            return ret;
    }


    int own(int[] nums, int target) {
        return rec(nums, target, 0, nums.length);
    }

    int rec(int[] nums, int target, int lb, int up) {
        if (up - lb > 1) {
            int half = up - (up - lb) / 2;
            int cur = nums[half];
            if (cur == target) {
                return half;
            } else {
                if (cur > target) {
                    return rec(nums, target, lb, half);
                } else {
                    return rec(nums, target, half, up);
                }
            }
        } else if (up == lb) {
            if (nums[up] == target)
                return up;
            else if (nums[up] < target)
                return up + 1;
            else
                return up;
        } else {
            if (nums[lb] > target)
                return lb;
            else if (nums[lb] == target)
                return lb;
            else if (nums[up] > target)
                return up;
            else if (nums[up] == target)
                return up;
            else
                return up + 1;
        }
    }
}


