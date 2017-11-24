package leetcode.p476;

public class Solution {

  public static void main(String[] args){
    Solution sol = new Solution();
    sol.findComplement(16);

  }

  public int findComplement(int num) {
    int mask = num;
    mask |= mask >> 1;
    mask |= mask >> 2;
    mask |= mask >> 4;
    mask |= mask >> 8;
    mask |= mask >> 16;
    mask |= 1;
    int flip = ~num;
    int res = flip & mask;
    return res;
  }
}
