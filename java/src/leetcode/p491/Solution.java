package leetcode.p491;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args) {

    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        Arrays.sort(nums);

        int len = (int) Math.pow(2,nums.length);
        List<List<Integer>> ret = new LinkedList<>();
        for (int i = 0; i < len ; i++)
        {
            BitSet bt = BitSet.valueOf(new long[]{i});
            List<Integer> lst = new LinkedList<>();
            for(int j = 0; j < bt.length();j++)
            {
                if(bt.get(j) == true){
                    lst.add(nums[j]);
                }
            }
            if (lst.size() > 1){
                ret.add(lst);
            }

        }
        return ret;

    }
}