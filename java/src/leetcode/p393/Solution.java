package leetcode.p393;

public class Solution {

  public static void main(String[] args){
    Solution sol = new Solution();
    int[] arr = {240,162,138,147};
    sol.validUtf8(arr);
  }

  public boolean validUtf8(int[] data) {

    for (int i = 0; i < data.length; ){
      int cur = data[i];
      if ( (cur & (1 << 7) ) != 0){ //8th bit set
        if (i+1 < data.length && (data[i] & 0b11100000) == 0b11000000 && (data[i+1] & 0b11000000) == 0b10000000 )
          i = i + 2;
        else {
          if (i+2 < data.length && (data[i] & 0b11110000) == 0b11100000 && (data[i+1] & 0b11000000) == 0b10000000 && (data[i+2] & 0b11000000) == 0b10000000)
            i = i + 3;
          else {
            if (i+3 < data.length && (data[i] & 0b11111000) == 0b11110000 && (data[i+1] & 0b11000000) == 0b10000000 && (data[i+2] & 0b11000000) == 0b10000000 && (data[i+3] & 0b11000000) == 0b10000000)
              i = i + 4;
            else {
              return false;
            }
          }
        }
      } else {
        i++; //1 byte version - ok
      }
    }
    return true;
  }
}
