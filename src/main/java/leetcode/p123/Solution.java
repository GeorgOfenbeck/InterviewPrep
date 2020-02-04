package leetcode.p123;


import java.util.*;


abstract class POI{
    int x;
    int pos;
}

class Min extends POI{
    Min(int x, int pos){
        this.pos = pos;
        this.x = x;
    }
}
class Max extends POI{
    Max(int x, int pos){
        this.pos = pos;
        this.x = x;
    }
}

class Profit{
    Min min;
    Max max;
    int profit;
    Profit( Min min, Max max){
        this.min = min;
        this.max = max;
        profit = max.x - min.x;
    }
}

class Result{

    Result(int size){
        k = size;
        sort = new TreeSet<>();
    }
    int k = 0;
    SortedSet<Profit> sort;
    Profit last;
    int profit(){
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        return sum;
    }

    int profitMaxK(Profit px){
        sort.add(px);
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        sum = sum- sort.last().profit; //remove smallest
        sort.remove(px);
        return sum + px.profit;
    }

    Profit getLast(){
        return last;
    }

    int profitMaxKwoLast(Profit px){
        sort.remove(last);
        int sum = 0;
        for (Profit p : sort)
            sum += p.profit;
        sort.add(last);
        return sum + px.profit;
    }

    void addSetandResult(Result other, Profit px){
        sort.clear();
        for (Profit p : other.sort){
            sort.add(p);
        }
        sort.add(px);
        last = px;
    }

    void add(Profit px){
        sort.add(px);
        last = px;
        if (sort.size() > this.k)
            sort.remove(sort.last()); //remove smallest
    }

    void removeLastandAdd(Profit px){
        sort.remove(last);
        sort.add(px);
        last = px;
    }

}


class Solution {
    public static void main(String [] args){
        int [] test = new int[]{1,2,3,4,5};
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(test));
    }

    public int maxProfit(int[] prices) {
        return maxProfit(prices,1);
    }
    public int maxProfit(int[] prices, int k) {
        ArrayList<Profit> profits = sanitize(prices);
        if (profits.isEmpty()) return 0;
        else {
            ArrayList<Result> results = new ArrayList<>();
            for (int i = 0 ; i < k; i ++){
                results.add(new Result(i+1));
            }
            for (int i = 0; i < profits.size(); i++){
                updateFront(results, profits.get(i));
            }
            //results.get(results.get(results.size()-1)).
        }

    }

    private Profit tmax(Profit p1, Profit p2, Profit p3){
        if (p1.profit >= p2.profit){
            if (p1.profit >= p3.profit)
                return p1;
            else
                return p3;
        } else
        if (p2.profit >= p3.profit)
            return p2;
        else
            return p3;
    }
    private Profit dmax(Profit p1, Profit p2){
        if (p1.profit >= p2.profit){
            return p1;
        } else return p2;
    }



    private void updateFront(ArrayList<Result> ks, Profit px){
        for (int i = 0; i < ks.size(); i++){
            Result prev = null;
            Result result = ks.get(i);
            int profitPrevAndPx;
            if (i > 0){
                prev = ks.get(i-1);
                profitPrevAndPx = prev.profit();
            } else {
                profitPrevAndPx = 0;
            }

            int profitMaxK = result.profitMaxK(px);

            Profit last = result.getLast();

            Profit merge = new Profit(last.min,px.max);

            int profitMaxKwoLast = result.profitMaxKwoLast(merge);

            if (profitMaxK > profitMaxKwoLast){
                if (profitMaxK > profitPrevAndPx) {
                    //profitMaxK
                    result.add(px);
                } else {
                    //profitPrevAndPx
                    result.addSetandResult(prev,px);
                }
            } else {
                if (profitMaxKwoLast > profitPrevAndPx) {
                    //profitMaxKwoLast
                    result.removeLastandAdd(merge);
                } else {
                    //profitPrevAndPx
                    result.addSetandResult(prev,px);
                }
            }
        }
    }


    private ArrayList<Profit> sanitize(int [] prices){
        LinkedList<POI> poi = new LinkedList<>();
        for (int i = 1; i < prices.length-1; i++){
            if (prices[i-1] <= prices[i] && prices[i+1] < prices[i])
                poi.add(new Max(prices[i],i));
            if (prices[i-1] >= prices[i] && prices[i+1] > prices[i])
                poi.add(new Min(prices[i],i));
        }
        if (!poi.isEmpty()) {
            if (poi.getFirst() instanceof Max)
                poi.add(new Min(prices[0], 0));
            if (poi.getLast() instanceof Min)
                poi.addLast(new Max(prices[prices.length - 1], prices.length - 1));
        } else {
            if (prices[0] < prices[prices.length-1]){
                poi.add(new Min(prices[0], 0));
                poi.add(new Max(prices[prices.length - 1], prices.length - 1));
            }
        }
        ArrayList<Profit> profits = new ArrayList<>();
        if (poi.isEmpty()) return profits;

        for (int i = 0; i < poi.size(); i = i+ 2){
            if (poi.get(i) instanceof Min && poi.get(i+1) instanceof Max)
            {
                Min min = (Min) poi.get(i);
                Max max = (Max) poi.get(i+1);
                Profit profit = new Profit(min,max);
                profits.add(profit);
            }
        }
        return profits;
    }
}
