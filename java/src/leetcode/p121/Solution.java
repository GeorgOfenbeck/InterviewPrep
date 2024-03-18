package leetcode.p121;

class Solution {

    public static void main(String[] args ){
        Solution sol = new Solution();
        int [] arr = {7,1,5,3,6,4};
        System.out.println(sol.maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        return maxProfit(prices,0,prices.length);
    }

    public int maxProfit(int [] prices, int start, int end){
        int min = prices[start];
        int max = prices[start];
        int profit = 0;
        for (int i = start; i < end; i++){
            int cur = prices[i];
            if (cur <= min){
                min = cur;
                max = min;
            }
            if (cur > max){
                max = cur;
                int localprof = max - min;
                if (localprof > profit)
                    profit = localprof;
            }
        }
        return profit;
    }
}