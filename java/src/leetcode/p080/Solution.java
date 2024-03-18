package leetcode.p080;


class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;

        int val = nums[0];
        boolean second = false;

        int to = 1;
        int cur;
        for (int from = 1; from < nums.length; from ++){
             cur = nums[from];
            if (cur == val){ //duplicate
                if (!second){ //its the first duplicate
                    second = true;
                    nums[to] = nums[from];
                    to++;
                } else { //we are at 3rd+ dup
                    //dont increase to
                }
            } else { //we are at a new value
                val = cur;
                second = false;
                nums[to] = nums[from];
                to++;
            }
        }
        return to + 1;
    }
}