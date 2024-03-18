package leetcode.p075;

class Solution {

    public static void main(String[] args){
        int [] a = {1,2,0};
        Solution sol = new Solution();
        sol.sortColors(a);
        for (int x: a)
            System.out.println(x);
    }

    public void sortColors(int[] nums) {

        int zero = 0;
        int two = nums.length - 1;

        for (int i = 0; i < nums.length && i <= two; ){
            int ele = nums[i];

            if (ele == 0){
                nums[i] = nums[zero];
                nums[zero] = ele;
                zero = zero + 1;
                i++;
            }
            else {
                if (ele == 1){
                    i++;
                } else {
                    //ele == 2
                    nums[i] = nums[two];
                    nums[two] = ele;
                    two = two - 1;
                }
            }
        }
    }
}