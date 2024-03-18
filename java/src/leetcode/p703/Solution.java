package leetcode.p703;

import java.util.Comparator;
import java.util.PriorityQueue;

class KthLargest {
    int k;

    public static void main(String [] args){
        int[] arr = {10,1,2,3,4,5};
        KthLargest sol = new KthLargest(3,arr);
    }

    PriorityQueue<Integer> minheap;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        minheap = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < nums.length; i++) {
            minheap.add(nums[i]);
            if (minheap.size() == k+1)
                minheap.poll();
        }
    }

    public int add(int val) {
        minheap.add(val);
        if (minheap.size() == k+1)
            return minheap.poll();
        else {
            return minheap.peek();
        }
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
