package leetcode.p215;

class Solution {
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] a = {2,54,1,23,9,1,91,-1};
        sol.quicksort(0,a.length-1,a);
        for (int i = 0; i < a.length;i++) {
            System.out.println(a[i]);
        }
    }



    public int findKthLargest(int[] nums, int k) {
        return 0;
    }


    void quicksort(int left, int right, int[] a) {
        if (right - left <= 0) return;
        if (right - left == 1)
        {
            if (a[left] > a[right]) swap(left,right,a);
        } else {
            int middle = left + (right - left) / 2;
            int pivot = a[middle];

            int i = left;
            int j = right;

            while (i <= j) {
                while (a[i] < pivot) i++;
                while (a[j] > pivot) j--;

                if (i <= j) {
                    swap(i, j, a);
                    i++;
                    j--;
                }
            }

            if(left < j) quicksort(left,j,a);
            if(i < right) quicksort(i,right,a);
        }

    }

    void swap(int i, int j, int[] a) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
