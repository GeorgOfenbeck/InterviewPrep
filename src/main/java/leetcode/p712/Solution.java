package leetcode.p712;



class Solution {

  public static void main(String[] args){
    Solution sol = new Solution();
    sol.minimumDeleteSum("cccca",
        "ac");
  }
  public int minimumDeleteSum(String s1, String s2) {
    int[][] nums = new int[s1.length()+1][s2.length()+1];
    int diag = 0;
    int top = 0;
    int left = 0;

    nums[0][0] = 0;
    for (int i = 1; i < s1.length()+1; i++){

      char a = s1.charAt(i-1);
      nums[i][0] = nums[i-1][0] + (int) a;
    }

    for (int i = 1; i < s2.length()+1; i++){
      char a = s2.charAt(i-1);
      nums[0][i] = nums[0][i-1] + (int) a;
    }


    for (int i = 1; i < s1.length()+1; i++){
      for (int j = 1; j< s2.length()+1; j++){
        char a = s1.charAt(i-1);
        char b = s2.charAt(j-1);

        if (a == b){
          nums[i][j] = nums[i-1][j-1];
        } else {
          if (nums[i-1][j] > nums[i][j-1]) //left is smaller
          {
            nums[i][j] = nums[i][j-1] + (int)b;
          } else
          {
            nums[i][j] = nums[i-1][j] + (int)a;
          }
        }

      }
    }
    return nums[s1.length()][s2.length()];
  }
}