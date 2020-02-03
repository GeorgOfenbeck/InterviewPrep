package leetcode.p123;


import java.util.*;

class Profit{
    int min;
    int max;
    Profit(int min, int max){
        this.min = min;
        this.max = max;
    }

    int profit() {
        return max - min;
    }
}



class Solution {
    public int maxProfit(int[] prices) {
        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();

        findMinMax(prices,min,max);




    }

    public void findMinMax(int [] prices, LinkedList<Integer> min, LinkedList<Integer> max){
        min.add(prices[0]);
        for (int i = 1; i < prices.length-1; i++){
            if (prices[i-1] < prices[i] && prices[i+1] < prices[i])
                max.add(prices[i]);
            if (prices[i-1] > prices[i] && prices[i+1] > prices[i])
                min.add(prices[i]);
        }
        max.add(prices[prices.length-1]);
    }


    public int maxProfit(int [] prices, int start, int end){
        int min = prices[start];
        int max = prices[start];
        int profit = 0;
        for (int i = start; i < end; i++){
            int cur = prices[i];
            if (cur < min){
                min = cur;
                max = min;
            }
            if (cur > max){
                int localprof = max - min;
                if (localprof > profit)
                    profit = localprof;
            }
        }
        return profit;
    }
}
