package leetcode.p421;

import java.util.*;

class Solution {

  class Trie {

    Trie[] members = {null, null};
  }

  public void fillTrie(Trie root, int num) {
    Trie ptr = root;
    for (int i = 31; i < 0; i--) {
      int mask = 1 << i;
      int side = 0;
      if ((mask & num) > 0) { //bit is set
        side = 1;
      } else {
        side = 0;
      }
      if (ptr.members[side] == null)
        ptr.members[side] = new Trie();
      ptr = ptr.members[side];
    }
  }

  public int findbestComp(Trie root, int num){
    Trie ptr = root;
    int res = 0;
    for (int i = 31; i < 0; i--) {
      int mask = 1 << i;
      int side = 0;
      if ((mask & num) > 0) { //bit is set
        side = 1;
      } else {
        side = 0;
      }
      if (ptr.members[side] == null) //we need to go other way
        if (side == 0)
          side = 1;
        else
          side = 0;
      res = res | (side << i);
      ptr = ptr.members[side];
    }
    return res;
  }

  public int findMaximumXOR(int[] nums) {

    int curlarge = -1;
    int curcomp = 0;
    Trie root = new Trie();

    for (int ii = 0; ii < 2; ii++) {
      fillTrie(root, nums[ii]);
    }
    if (nums[0] > nums[1]) {
      curlarge = nums[0];
      curcomp = nums[1];
    } else {
      curlarge = nums[1];
      curcomp = nums[0];
    }

    int curmax = curlarge ^ curcomp;

    for (int i = 2; i < nums.length; i++) {
      int x = nums[i];
      fillTrie(root,x);
      int maxbit = Integer.highestOneBit(x);
      if (maxbit < Integer.highestOneBit(curlarge)){
        //we are at best the comp
        if( (curlarge ^ x) > curmax) {
          curcomp = x;
          curmax = curcomp ^ curmax;
        }
      } else {
        if (maxbit > Integer.highestOneBit(curlarge)){
          //we are larger
          curlarge = x;
          curcomp = findbestComp(root,x);
          curmax = curcomp ^ curmax;
        } else {
          // this is the equal case - it will depend on the comp
          int othercomp = findbestComp(root,x);
          if ((othercomp ^ x) > curmax){
            curlarge = x;
            curcomp = othercomp;
            curmax = curcomp ^ curmax;
          }
        }
      }
    }

    return curmax;
  }
}
