package leetcode.p188;

import java.util.*;


import java.util.PriorityQueue;


class RefinedResultImmutable {
    Profit lastSelectedProfit = null;
    Profit lowestNonSelectMin = null;
    int curPos = -1;
    int k = 0;
    PriorityQueue<Profit> heap;
    int profit = 0;

    RefinedResultImmutable(int size) {
        k = size;
        heap = new PriorityQueue<>(Collections.reverseOrder());
    }

    void printResult() {
        System.out.print("-> " + k + " ");
        for (Profit p : heap) {
            System.out.print(p.min.x + " ^ " + p.max.x + " ");
        }
        System.out.println();
    }

    int getProfit() {
        if (heap.isEmpty()) return Integer.MIN_VALUE;
        else return profit;
    }

    Profit peekSmallest() {
        return heap.peek();
    }

    private RefinedResultImmutable addToHeap(Profit px) {
        RefinedResultImmutable copy = this;
        copy.heap.add(px);
        if (copy.heap.size() > k) {
            Profit smallest = copy.heap.poll(); //remove the smallest
            //assert (smallest != px);
            copy.profit = copy.profit - smallest.profit + px.profit;
        } else {
            copy.profit = copy.profit + px.profit;
        }
        return copy;
    }

    RefinedResultImmutable add(Profit px, int k) {
        return add(px, new RefinedResultImmutable(k));
    }

    RefinedResultImmutable add(Profit px) {
        return add(px, new RefinedResultImmutable(this.k));
    }

    //this assumes that it already is the current Max
    RefinedResultImmutable add(Profit px, RefinedResultImmutable target) {
        RefinedResultImmutable copy = target;
        copy.heap.addAll(heap);
        copy.curPos = px.max.pos;
        copy.lowestNonSelectMin = null;
        copy.lastSelectedProfit = px;
        copy.profit = profit;
        return copy.addToHeap(px);
    }

    //take the previous heap + pk
    RefinedResultImmutable newHeap(Profit px, RefinedResultImmutable km1) {
        RefinedResultImmutable copy = new RefinedResultImmutable(k);
        copy.lowestNonSelectMin = null;
        copy.lastSelectedProfit = px;
        copy.heap.addAll(km1.heap);
        copy.profit = km1.profit;
        return copy.add(px);

    }

    RefinedResultImmutable removeLastSelectedProfit() {
        RefinedResultImmutable copy = new RefinedResultImmutable(k);
        copy.lowestNonSelectMin = null;
        copy.lastSelectedProfit = null;
        for (Profit p : heap) {
            if (p != lastSelectedProfit)
                copy.heap.add(p);
        }
        copy.profit = profit - lastSelectedProfit.profit;
        return copy;
    }

}

abstract class Option {
    int profit;

    abstract RefinedResultImmutable add();

    abstract int getProfit();
}


class AddPx extends Option {
    RefinedResultImmutable sofar;
    Profit px;

    AddPx(RefinedResultImmutable sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        profit = getProfit();
    }

    int getProfit() {
        if (sofar.heap.size() == sofar.k)
            return sofar.profit - sofar.peekSmallest().profit + px.profit;
        else
            return sofar.profit + px.profit;
    }

    RefinedResultImmutable add() {
        return sofar.add(px);
    }
}

class AddNewHeapPx extends Option {
    RefinedResultImmutable prev;
    RefinedResultImmutable sofar;
    Profit px;

    AddNewHeapPx(RefinedResultImmutable prev, RefinedResultImmutable sofar, Profit px) {
        this.prev = prev;
        this.px = px;
        this.sofar = sofar;
        profit = getProfit();
    }

    int getProfit() {
        return prev.profit + px.profit;
    }

    RefinedResultImmutable add() {

        return sofar.newHeap(px, prev);
    }
}


class MergeLast extends Option {
    RefinedResultImmutable sofar;
    Profit px;
    Profit merge;

    MergeLast(RefinedResultImmutable sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        merge = new Profit(sofar.lastSelectedProfit.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit - sofar.lastSelectedProfit.profit + merge.profit;
    }

    RefinedResultImmutable add() {
        RefinedResultImmutable minus = sofar.removeLastSelectedProfit();
        RefinedResultImmutable change = minus.add(merge);
        return change;
        //boolean worked = sofar.heap.remove(sofar.lastSelectedProfit);
        //sofar.profit = sofar.profit - sofar.lastSelectedProfit.profit;
    }
}

class MergeLastNonSelected extends Option {
    RefinedResultImmutable sofar;
    Profit px;
    Profit merge;
    int previdx;
    ArrayList<RefinedResultImmutable> prevs;

    MergeLastNonSelected(RefinedResultImmutable sofar, Profit px, ArrayList<RefinedResultImmutable> prevs, int previdx) {
        this.sofar = sofar;
        this.px = px;
        this.prevs = prevs;
        this.previdx = previdx;
        merge = new Profit(sofar.lowestNonSelectMin.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        return prevs.get(previdx).add(merge, sofar.k).profit;//prevs.get(previdx).profit + merge.profit;
    }

    RefinedResultImmutable add() {
        return prevs.get(previdx).add(merge, sofar.k);
    }
}

class Discard extends Option {
    RefinedResultImmutable sofar;
    Profit px;

    Discard(RefinedResultImmutable sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit;
    }

    RefinedResultImmutable add() {
        if (sofar.lowestNonSelectMin == null || sofar.lowestNonSelectMin.min.x >= px.min.x) {
            sofar.lowestNonSelectMin = px;
        }
        return sofar;
    }
}


abstract class POI {
    int x;
    int pos;
}

class Min extends POI {
    Min(int x, int pos) {
        this.pos = pos;
        this.x = x;
    }
}

class Max extends POI {
    Max(int x, int pos) {
        this.pos = pos;
        this.x = x;
    }
}

class Profit implements Comparable<Profit> {
    Min min;
    Max max;
    int profit;

    Profit(Min min, Max max) {
        this.min = min;
        this.max = max;
        profit = max.x - min.x;
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Profit)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Profit c = (Profit) o;

        // Compare the data members and return accordingly
        return Integer.compare(min.x, c.min.x) == 0 && Integer.compare(min.pos, c.min.pos) == 0 &&
                Integer.compare(max.x, c.max.x) == 0 && Integer.compare(max.pos, c.max.pos) == 0;
    }

    @Override
    public int compareTo(Profit o) {
        if (o.profit < this.profit)
            return -1;
        else if (o.profit > this.profit)
            return 1;
        else if (o.max.pos < this.max.pos)
            return 1;
        else if (o.max.pos == this.max.pos)
            return 0;
        else
            return -1;
    }
}


public class Solution {
    boolean debug = false;
    public static void main(String[] args) {
        //int [] test = new int[]{1,2,-10,4,-20,5};//
        //int [] test = new int[]{3,3,5,0,0,3,1,4};[1,1,0]
        //int [] test = new int[]{2,1,2,0,1,0,10};
        //int [] test = new int[]{1,2,4,2,5,7,2,4,9,0};
        //int [] test = new int[]{5,5,4,9,3,8,5,5,1,6,8,3,4};
        //int [] test = new int[]{-1, 2147483647};
        //int[] test = new int[]{0, 4, 0, 25, 12, 15, 14, 18};
        //int[] test = new int[]{0, 10, 0, 32, 25, 35};
        //int[] test = new int[]{0, 4, 1, 9, 0, 3, 1, 4};
        //int[] test = new int[]{1,3};
        //int[] test =  new int[]{0, 1, 3, 0, 5};
        //int[] test = new int[]{0, 7, 3, 9, 0, 5};
        //int[] test = new int[]{0, 2, 0, 3, 0, 2, 1, 4};
        //int[] test = new int[]{0, 3, 0, 8, 7, 10, 0, 1};
        //int[] test = new int[]{1, 7, 0, 3, 1, 8, 0, 2, 1, 8};
        //int[] test = new int[]{0, 4, 1, 3, 1, 8, 0, 7};
        //int[] test = new int[]{2,6,8,7,8,7,9,4,1,2,4,5,8};
        int[] test = new int[]{2,6,8,7,8,7,9,4,1,2,4,5,8};
        Solution sol = new Solution();
        sol.debug = true;
        //System.out.println(sol.maxProfit(test));
        System.out.println(sol.maxProfit(2, test));
        //System.out.println(sol.maxProfitn2(test));


    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        return maxProfit(prices, 2);
        //return maxProfitn2(prices);
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;
        return maxProfit(prices, k);
    }

    public void printExtremas(ArrayList<Profit> profits) {
        if (!debug) return;
        for (Profit p : profits) {
            System.out.print(p.min.x + " ^ " + p.max.x + " ");
        }
        System.out.println();
    }


    public int maxProfitn2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] pricesNoDup = removeDups(prices);
        int max = 0;
        int j = 0;
        for (int i = 0; i < pricesNoDup.length; i++) {
            int a = maxProfit1(pricesNoDup, 0, i);
            int b = maxProfit1(pricesNoDup, i, pricesNoDup.length);
            int c = a + b;
            if (c > max) {
                j = i;
                max = c;
            }
        }
        /*System.out.println(j);
        System.out.println( maxProfit1(pricesNoDup,0,j));
        System.out.println(maxProfit1(pricesNoDup,j,pricesNoDup.length));*/
        return max;
    }

    public int maxProfit1(int[] prices, int start, int end) {
        int min = prices[start];
        int max = prices[start];
        int profit = 0;
        for (int i = start; i < end; i++) {
            int cur = prices[i];
            if (cur <= min) {
                min = cur;
                max = min;
            }
            if (cur > max) {
                max = cur;
                int localprof = max - min;
                if (localprof > profit)
                    profit = localprof;
            }
        }
        return profit;
    }


    public int maxProfit(int[] prices, int k) {
        int[] pricesNoDup = removeDups(prices);
        ArrayList<Profit> profits = sanitize(pricesNoDup);
        printExtremas(profits);
        if (profits.isEmpty()) return 0;

        if (k >= prices.length){
            int sum = profits.stream().map(x -> x.profit).reduce( (a,b) -> a + b).get();
            return sum;
        }


        ArrayList<RefinedResultImmutable> prev = null;

        for (int j = 0; j < k; j++) {
            ArrayList<RefinedResultImmutable> cur = new ArrayList<>();
            RefinedResultImmutable result = new RefinedResultImmutable(j + 1);
            int profitidx = 0;
            for (Profit px : profits) {
                LinkedList<Option> options = new LinkedList<>();
                Discard discard = new Discard(result, px);
                options.add(discard);
                AddPx addPx = new AddPx(result, px);
                options.add(addPx);
                if (result.lastSelectedProfit != null) {
                    MergeLast ml = new MergeLast(result, px);
                    options.add(ml);
                }
                if (result.lowestNonSelectMin != null) {
                    MergeLastNonSelected mls;
                    if (prev == null)
                        mls = new MergeLastNonSelected(result, px, cur, profitidx - 1);
                    else
                        mls = new MergeLastNonSelected(result, px, prev, profitidx - 1);
                    options.add(mls);
                }
                if (profitidx > 0 && prev != null) {
                    RefinedResultImmutable km1 = prev.get(profitidx - 1);
                    AddNewHeapPx newHeapPx = new AddNewHeapPx(km1, result, px);
                    options.add(newHeapPx);
                }
                Comparator<Option> comparator = Comparator.comparing(Option::getProfit, (s1, s2) -> s1.compareTo(s2));
                Option max = options.stream().max(comparator).get();
                RefinedResultImmutable copy = max.add();
                result = copy;
                cur.add(copy);
                profitidx++;
            }
            prev = cur;
        }
        return prev.get(prev.size() - 1).profit;
    }
/*
    public int maxProfit_streaming(int[] prices, int k) {
        int[] pricesNoDup = removeDups(prices);
        ArrayList<Profit> profits = sanitize(pricesNoDup);
        //printExtremas(profits);
        if (profits.isEmpty()) return 0;
        else {
            ArrayList<RefinedResult> results = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                results.add(new RefinedResult(i + 1));
            }
            for (int i = 0; i < profits.size(); i++) {
                updateFront(results, profits.get(i));
            }

            int max = Integer.MIN_VALUE;
            for (RefinedResult r : results) {
                if (r.profit > max)
                    max = r.profit;
            }
            return max;
            //results.get(results.get(results.size()-1)).
        }

    }

 */

    private Profit tmax(Profit p1, Profit p2, Profit p3) {
        if (p1.profit >= p2.profit) {
            if (p1.profit >= p3.profit)
                return p1;
            else
                return p3;
        } else if (p2.profit >= p3.profit)
            return p2;
        else
            return p3;
    }

    private Profit dmax(Profit p1, Profit p2) {
        if (p1.profit >= p2.profit) {
            return p1;
        } else return p2;
    }


    /*
        private void updateFront(ArrayList<RefinedResult> ks, Profit px) {
            for (int i = ks.size()-1; i >= 0; i--) {

                RefinedResult result = ks.get(i);


                LinkedList<Option> options = new LinkedList<>();

                RefinedResult xx;
                if (i == 0) xx = null;
                else xx = ks.get(i-1);
                Discard discard = new Discard(result, px,xx);
                options.add(discard);
                AddPx addPx = new AddPx(result, px);
                options.add(addPx);

                if (result.lowestNonSelectMin != null) {
                    MergeLastNonSelected mls = new MergeLastNonSelected(result, px);
                    options.add(mls);
                }
                if (result.lastSelectedProfit != null) {
                    MergeLast ml = new MergeLast(result, px);
                    options.add(ml);
                }
                if (px.min.pos > 0 && i != 0) {
                    RefinedResult prev = ks.get(i-1);
                    AddNewHeapPx newHeapPx = new AddNewHeapPx(prev, result, px);
                    options.add(newHeapPx);
                }


                Comparator<Option> comparator = Comparator.comparing( Option::getProfit, (s1,s2) -> s1.compareTo(s2));
                Option max = options.stream().max(comparator).get();
                max.add();
                //result.printResult();
            }
        }
    */
    private int[] removeDups(int[] prices) {
        if (prices == null) return null;
        if (prices.length < 2) return prices;
        LinkedList<Integer> buffer = new LinkedList<>();

        int last = prices[0];
        buffer.add(prices[0]);
        int pos = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] != last) {
                buffer.add(prices[i]);
                last = prices[i];
            }
        }
        int[] ret = new int[buffer.size()];
        pos = 0;
        for (Integer e : buffer) {
            ret[pos] = e;
            pos++;
        }
        return ret;

    }


    private ArrayList<Profit> sanitize(int[] prices) {
        if (prices == null || prices.length == 0) return new ArrayList<>();


        LinkedList<POI> poi = new LinkedList<>();
        for (int i = 1; i < prices.length - 1; i++) {
            if (prices[i - 1] <= prices[i] && prices[i + 1] < prices[i])
                poi.add(new Max(prices[i], i));
            if (prices[i - 1] >= prices[i] && prices[i + 1] > prices[i])
                poi.add(new Min(prices[i], i));
        }
        if (!poi.isEmpty()) {
            if (poi.getFirst() instanceof Max)
                poi.addFirst(new Min(prices[0], 0));
            if (poi.getLast() instanceof Min)
                poi.addLast(new Max(prices[prices.length - 1], prices.length - 1));
        } else {
            if (prices[0] < prices[prices.length - 1]) {
                poi.add(new Min(prices[0], 0));
                poi.add(new Max(prices[prices.length - 1], prices.length - 1));
            }
        }
        ArrayList<Profit> profits = new ArrayList<>();
        if (poi.isEmpty()) return profits;

        for (int i = 0; i < poi.size(); i = i + 2) {
            if (poi.get(i) instanceof Min && poi.get(i + 1) instanceof Max) {
                Min min = (Min) poi.get(i);
                Max max = (Max) poi.get(i + 1);
                Profit profit = new Profit(min, max);
                profits.add(profit);
            }
        }
        int i = 0;

        return profits;
    }
}
