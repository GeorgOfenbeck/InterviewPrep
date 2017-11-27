package leetcode.p689;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


class Solution {

  int[][] sums;
  int [][] tsum;
  Node[][] indicies;
  int[] nsums;

  class Node{
    int value = 0;
    Node next = null;
  }

  public static void main(String[] args){

    int[] par = {7,13,20,19,19,2,10,1,1,19};
    Solution sol = new Solution();
    int[] res = sol.maxSumOfThreeSubarrays(par,3);
    Arrays.stream(res).forEach(p -> System.out.println(p));
  }

  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    //sums = new int[nums.length][nums.length];
    tsum = new int[nums.length][3];
    nsums = new int[nums.length];
    indicies = new Node[nums.length][3];
    presum(nums,k);
    int max = threes(nums,0,nums.length-1,2,k);
    Node fin = indicies[0][2];
    Node ptr = fin;
    int res[] = new int[3];
    int i = 0;
    while (ptr != null){
      res[i] = ptr.value;
      ptr = ptr.next;
      i++;
    }
    return res;
  }
  public void presum(int [] nums, int k) {

    int acc = 0;
    for (int j = 0; j < k; j++)
      acc = acc + nums[j];

    nsums[0] = acc;

    for (int i = 1; i <= nums.length-k; i++){
      acc = acc - nums[i-1] + nums[i+k-1];
      nsums[i] = acc;
    }
  }

  public int sum(int[] nums, int i, int j) {
    if (true)
      return nsums[i];
    if (sums[i][j] > 0) {
      return sums[i][j];
    } else {
      if (i == j) {
        int res = nums[i];
        sums[i][j] = res;
        return res;
      } else {
        if (j < i) {
          return 0;
        } else {
          int res = sum(nums, i, i) + sum(nums, i + 1, j);
          sums[i][j] = res;
          return res;
        }
      }
    }
  }

  public int threes(int [] nums, int i, int j, int r, int k){

    if (r == -1) return 0;
    if (i+k*(r+1)-1 > j)
      return Integer.MIN_VALUE;
    if (tsum[i][r] > 0)
      return tsum[i][r];
    else {
      int take = sum(nums,i,i+k-1) + threes(nums,i+k,j,r-1,k);
      int notake = threes(nums,i+1,j,r,k);
      if (take >= notake){
        tsum[i][r] = take;
        if (indicies[i][r] == null)
          indicies[i][r] = new Node();
        Node newnode = indicies[i][r];
        Node sofar;
        if (i + k <= j && r > 0)
          sofar = indicies[i+k][r-1];
        else
          sofar = null;
        newnode.next = sofar;
        newnode.value = i;
      } else
      {
        tsum[i][r] = notake;
        indicies[i][r] = indicies[i+1][r];
      }
      return tsum[i][r];
    }
  }
}