package leetcode.p477;

public class Solution {

  public static void main (String [] args){
    Solution sol = new Solution();
    int[] arr = {4,14,2};
    int res = sol.totalHammingDistance(arr);
    System.out.println(res);
  }

  public int totalHammingDistance(int[] nums) {
    int[] mask = new int[32];
    int[] counts = new int[32];
    for (int i = 0; i < 32; i++){
      mask[i] = 1 << i;
      counts[i] = 0;
    }

    for (int num : nums){
      for (int i = 0; i < 32; i++){
        if ( (num & mask[i]) != 0)
          counts[i]++;
      }
    }

    int total = 0;
    for (int i = 0; i < 32; i++){
        total = total + (nums.length-counts[i]) * counts[i];
    }
    return total;

  }
}
