package leetcode.p283;

class Solution {
    public void moveZeroes(int[] nums) {

        int to = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                //don't move to
            } else {
                if (to != i)
                    nums[to] = nums[i];
                to ++;
            }
        }
        for (int j = to; j < nums.length; j++)
            nums[j] = 0;
    }
}