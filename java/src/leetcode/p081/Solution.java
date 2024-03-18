package leetcode.p081;



import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] arr = {1,1,3,1,1};
        Solution sol = new Solution();
         sol.search(arr, 15);
        //System.out.println(idx);
    }

    boolean search(int[] nums, int target) {
        if (nums == null) return false;
        if (nums.length == 0) return false;
        if (nums.length == 1)
            if (nums[0] == target) return true;
            else return false;


        if (nums[0] < nums[nums.length-1]){ //its not rotated
            return nt1(Arrays.binarySearch(nums, 0, nums.length , target));
        }
        int ini = nums[0];
        int i = 0;

        if (ini == target)
            return true;


        while(i+1 < nums.length && nums[i] == nums[i+1])
            i++;

        if (i == nums.length-1) //only duplicates
            return false;



        int idx = findbreak(nums, ini, i+1, nums.length);
        //System.out.println(idx);
         if (target < nums[0]) {
            return nt1(Arrays.binarySearch(nums, idx, nums.length , target));
        } else
            return nt1(Arrays.binarySearch(nums, 0, idx , target));
    }

    boolean nt1 (int num) {
        if (num < 0) return false;
        else return true;
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