package leetcode.p034;

class Solution {

    public static void main(String[] args) {
        int[] a = {5, 7, 7, 8, 8, 10};
        int target = 6;
        Solution sol = new Solution();
        int res[] = sol.searchRange(a, target);
        System.out.println(res);

    }


    public int[] searchRange(int[] nums, int target) {
        Ret ini = recurseI(nums, target, 0, nums.length - 1);

        if (ini == null) {
            int[] err = {-1, -1};
            return err;
        } else {
            int left = ini.left;
            int right = ini.right;
            int middle = left + (right - left) / 2;

            //refine left
            int lmiddle = middle;
            int rmiddle = middle;
            Ret rleft = recurseI(nums, target, left, middle - 1);
            while (rleft != null) {
                left = rleft.left;
                lmiddle = rleft.middle;
                rleft = recurseI(nums, target, left, lmiddle - 1);
            }

            //refine right
            Ret rright = recurseI(nums, target, middle + 1, right);
            while (rright != null) {
                right = rright.right;
                rmiddle = rright.middle;
                rright = recurseI(nums, target, rmiddle + 1, right);
            }

            int[] ret = {lmiddle, rmiddle};
            return ret;
        }

    }

    class Ret {
        int left;
        int right;
        int middle;

        Ret(int left, int right) {
            this.left = left;
            this.right = right;
            this.middle = left + (right - left) / 2;
        }
    }


    Ret recurseI(int[] nums, int target, int left, int right) {
        while(true) {
            if (left > right) return null;
            else if (right - left <= 1) {
                if (nums[left] == target)
                    return new Ret(left, right);
                else
                    if (nums[right] == target)
                        return new Ret(right, right);
                    else
                        return null;
            } else {
                int middle = left + (right - left) / 2;
                if (nums[middle] == target)
                    return new Ret(left, right);
                else if (nums[middle] < target){
                    left = middle;
                }
                else {
                    right = middle;
                }
            }
        }
    }

    Ret recurse(int[] nums, int target, int left, int right) {
        if (left > right) return null;
        else if (left == right) {
            if (nums[left] == target)
                return new Ret(left, right);
            else
                return null;
        } else {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target)
                return new Ret(left, right);
            else if (nums[middle] < target)
                return recurse(nums, target, middle, right);
            else
                return recurse(nums, target, left, middle);

        }
    }

}
/*
    public int[] searchRange_it(int[] nums, int target) {


        //If the target is not found in the array, return [-1, -1].
        if (nums == null) return null;
        int left = 0;
        int right = nums.length;

        int[] ini = searchRange(nums, target, left, right);
        if (ini[0] == -1)
            return ini;
        else {
            left = ini[0];
            right = ini[1];

            int[] refleft;

            int lleft = left;
            int rright = right;
            do {
                int middle = lleft + (rright - lleft) / 2;
                refleft = searchRange(nums, target, lleft, middle - 1);
                if (refleft[0] != -1) {
                    lleft = refleft[0];
                    rright = refleft[1];
                }
            }
            while (refleft[0] != 0);

            int middle = lleft + (rright - lleft) / 2;
            int lres = middle;

            int[] error = {-1, -1};
            return error;
        }
    }

    int[] searchRange(int[] nums, int target, int left, int right) {

        if (nums.length == 0) {
            int[] error = {-1, -1};
            return error;
        }


        int cur = target + 1;
        int middle = left + (right - left) / 2;
        while (right - left >= 0 && cur != target) {

            cur = nums[middle];
            if (cur == target) {

            } else {
                if (cur < target) {
                    right = middle;
                } else {
                    left = middle;
                }
            }
        }

        if (right == left) {
            int[] error = {-1, -1};
            return error;
        } else {
            int[] ret = {left, right};
            return ret;
        }
    }
}
*/