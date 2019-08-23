package leetcode.p128;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        return findlength(0,set);

    }

    int findlength(int maxSofar,Set<Integer> set){
        
        while (!set.isEmpty() || set.size() < maxSofar){
            Iterator<Integer> it = set.iterator();
            Integer head =  it.next();
            int count = 1;
            int right = head +1;
            while (set.contains(right)){
                set.remove(right);
                count ++;
                right++;
            }
            int left = head -1;
            while (set.contains(left)){
                set.remove(left);
                count ++;
                left--;
            }
            if (count > maxSofar) maxSofar = count;
        }
        return maxSofar;
    }
}
