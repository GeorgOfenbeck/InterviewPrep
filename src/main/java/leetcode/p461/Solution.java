package leetcode.p461;

import java.util.*;

public class Solution
{
  public int hammingDistance(int x, int y) {
    int z = x ^ y;
    return 32 - Integer.bitCount(z);
  }
}
