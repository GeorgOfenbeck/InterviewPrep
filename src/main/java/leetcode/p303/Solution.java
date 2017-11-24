package leetcode.p303;
class NumArray {


  static public void main(String[] args){
    int[] arr = {-2,0,3,-5,2,-1};
    NumArray na = new NumArray(arr);
    int res = na.sumRange(0,5);
    System.out.println(res);
  }

  int[][] matrix;
  public NumArray(int[] nums) {
    matrix = new int[nums.length][nums.length];
    for (int i = 0; i< nums.length; i++){
      matrix[i][i] = nums[i];
    }
    for (int i = 1; i < nums.length; i++){
      for (int j = 0; j < i; j++){
        matrix[i][j] = matrix[i-1][j] + matrix[i][i];
      }
    }
  }

  public int sumRange(int i, int j) {
    return matrix[j][i];
  }
}
