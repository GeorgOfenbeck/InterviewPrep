package leetcode.p123;


import java.util.*;


import java.util.PriorityQueue;


class RefinedResult{

    Profit lastSelectedProfit = null;
    Profit lowestNonSelectMin = null;
    PriorityQueue<Profit> discardheap;
    int curPos = -1;
    int k = 0;
    PriorityQueue<Profit> heap;
    int profit = 0;

    RefinedResult(int size){
        k = size;
        heap = new PriorityQueue<>(Collections.reverseOrder());
    }

    void printResult(){
        System.out.print("-> " + k + " ");
        for (Profit p : heap){
            System.out.print(p.min.x + " ^ " + p.max.x + " ");
        }
        System.out.println();
    }

    int getProfit(){
        if (heap.isEmpty()) return Integer.MIN_VALUE;
        else return profit;
    }

    Profit peekSmallest(){
        return heap.peek();
    }

    void addToHeap(Profit px){
        heap.add(px);
        if (heap.size() > k) {
            Profit smallest = heap.poll(); //remove the smallest
            assert (smallest != px);
            profit = profit - smallest.profit + px.profit;
        } else {
            profit = profit + px.profit;
        }
    }

    //this assumes that it already is the current Max
    void add(Profit px){
        curPos = px.max.pos;
        addToHeap(px);
        lowestNonSelectMin = null;
        lastSelectedProfit = px;
    }

    //take the previous heap + pk
    void newHeap(Profit px, RefinedResult km1){
        lowestNonSelectMin = null;
        lastSelectedProfit = px;
        heap.clear();
        heap.addAll(km1.heap);
        profit = km1.profit;
        add(px);
    }



}

abstract class Option {
    int profit;

    abstract void add();

    abstract int getProfit();
}


class AddPx extends Option {
    RefinedResult sofar;
    Profit px;

    AddPx(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        profit = getProfit();
    }

    int getProfit() {
        if(sofar.heap.size() == sofar.k)
            return sofar.profit - sofar.peekSmallest().profit + px.profit;
        else
            return sofar.profit  + px.profit;
    }

    void add() {
        sofar.add(px);
    }
}

class AddNewHeapPx extends Option {
    RefinedResult prev;
    RefinedResult sofar;
    Profit px;

    AddNewHeapPx(RefinedResult prev, RefinedResult sofar, Profit px) {
        this.prev = prev;
        this.px = px;
        this.sofar = sofar;
        profit = getProfit();
    }

    int getProfit() {
        return prev.profit + px.profit;
    }

    void add() {
        sofar.newHeap(px, prev);
    }
}


class MergeLast extends Option {
    RefinedResult sofar;
    Profit px;
    Profit merge;

    MergeLast(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        merge = new Profit(sofar.lastSelectedProfit.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit - sofar.lastSelectedProfit.profit + merge.profit;
    }

    void add() {
        boolean worked = sofar.heap.remove(sofar.lastSelectedProfit);
        sofar.profit = sofar.profit - sofar.lastSelectedProfit.profit;
        sofar.add(merge);
    }
}

class MergeLastNonSelected extends Option {
    RefinedResult sofar;
    Profit px;
    Profit merge;

    MergeLastNonSelected(RefinedResult sofar, Profit px) {
        this.sofar = sofar;
        this.px = px;
        merge = new Profit(sofar.lowestNonSelectMin.min, px.max);
        profit = getProfit();
    }

    int getProfit() {
        int sum = 0;
        if (sofar.discardheap != null) {
            for (Profit p : sofar.discardheap)
                sum = sum + p.profit;
        }
        return sum + merge.profit;
        //return sofar.profit - sofar.peekSmallest().profit + merge.profit;
    }

    void add() {
        if (sofar.discardheap != null) {
            sofar.heap.clear();
            sofar.heap.addAll(sofar.discardheap);
            sofar.profit = profit - merge.profit;
        }
        sofar.add(merge);
    }
}

class Discard extends Option {
    RefinedResult sofar;
    RefinedResult prev;
    Profit px;

    Discard(RefinedResult sofar, Profit px, RefinedResult prev) {
        this.sofar = sofar;
        this.px = px;
        this.prev = prev;
        profit = getProfit();
    }

    int getProfit() {
        return sofar.profit;
    }

    void add() {
        if (sofar.lowestNonSelectMin == null || sofar.lowestNonSelectMin.min.x >= px.min.x) {
            sofar.lowestNonSelectMin = px;
            if (prev != null) {
                sofar.discardheap = new PriorityQueue<>();
                sofar.discardheap.addAll(prev.heap);
            }
        }
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
    public static void main(String[] args) {
        //int [] test = new int[]{1,2,-10,4,-20,5};//
        //int [] test = new int[]{3,3,5,0,0,3,1,4};[1,1,0]
        //int [] test = new int[]{2,1,2,0,1,0,10};
        //int [] test = new int[]{1,2,4,2,5,7,2,4,9,0};
        //int [] test = new int[]{5,5,4,9,3,8,5,5,1,6,8,3,4};
        //int [] test = new int[]{397,6621,4997,7506,8918,1662,9187,3278,3890,514,18,9305,93,5508,3031,2692,6019,1134,1691,4949,5071,799,8953,7882,4273,302,6753,4657,8368,3942,1982,5117,563,3332,2623,9482,4994,8163,9112,5236,5029,5483,4542,1474,991,3925,4166,3362,5059,5857,4663,6482,3008,3616,4365,3634,270,1118,8291,4990,1413,273,107,1976,9957,9083,7810,4952,7246,3275,6540,2275,8758,7434,3750,6101,1359,4268,5815,2771,126,478,9253,9486,446,3618,3120,7068,1089,1411,2058,2502,8037,2165,830,7994,1248,4993,9298,4846,8268,2191,3474,3378,9625,7224,9479,985,1492,1646,3756,7970,8476,3009,7457,8922,2980,577,2342,4069,8341,4400,2923,2730,2917,105,724,518,5098,6375,5364,3366,8566,8838,3096,8191,2414,2575,5528,259,573,5636,4581,9049,4998,2038,4323,7978,8968,6665,8399,7309,7417,1322,6391,335,1427,7115,853,2878,9842,2569,2596,4760,7760,5693,9304,6526,8268,4832,6785,5194,6821,1367,4243,1819,9757,4919,6149,8725,7936,4548,2386,5354,2222,8777,2041,1,2245,9246,2879,8439,1815,5476,3200,5927,7521,2504,2454,5789,3688,9239,7335,6861,6958,7931,8680,3068,2850,1181,1793,7138,2081,532,2492,4303,5661,885,657,4258,131,9888,9050,1947,1716,2250,4226,9237,1106,6680,1379,1146,2272,8714,8008,9230,6645,3040,2298,5847,4222,444,2986,2655,7328,1830,6959,9341,2716,3968,9952,2847,3856,9002,1146,5573,1252,5373,1162,8710,2053,2541,9856,677,1256,4216,9908,4253,3609,8558,6453,4183,5354,9439,6838,2682,7621,149,8376,337,4117,8328,9537,4326,7330,683,9899,4934,2408,7413,9996,814,9955,9852,1491,7563,421,7751,1816,4030,2662,8269,8213,8016,4060,5051,7051,1682,5201,5427,8371,5670,3755,7908,9996,7437,4944,9895,2371,7352,3661,2367,4518,3616,8571,6010,1179,5344,113,9347,9374,2775,3969,3939,792,4381,8991,7843,2415,544,3270,787,6214,3377,8695,6211,814,9991,2458,9537,7344,6119,1904,8214,6087,6827,4224,7266,2172,690,2966,7898,3465,3287,1838,609,7668,829,8452,84,7725,8074,871,3939,7803,5918,6502,4969,5910,5313,4506,9606,1432,2762,7820,3872,9590,8397,1138,8114,9087,456,6012,8904,3743,7850,9514,7764,5031,4318,7848,9108,8745,5071,9400,2900,7341,5902,7870,3251,7567,2376,9209,9000,1491,7030,2872,7433,1779,362,5547,7218,7171,7911,2474,914,2114,8340,8678,3497,2659,2878,2606,7756,7949,2006,656,5291,4260,8526,4894,1828,7255,456,7180,8746,3838,6404,6179,5617,3118,8078,9187,289,5989,1661,1204,8103,2,6234,7953,9013,5465,559,6769,9766,2565,7425,1409,3177,2304,6304,5005,9559,6760,2185,4657,598,8589,836,2567,1708,5266,1754,8349,1255,9767,5905,5711,9769,8492,3664,5134,3957,575,1903,3723,3140,5681,5133,6317,4337,7789,7675,3896,4549,6212,8553,1499,1154,5741,418,9214,1007,2172,7563,8614,8291,3469,677,4413,1961,4341,9547,5918,4916,7803,9641,4408,3484,1126,7078,7821,8915,1105,8069,9816,7317,2974,1315,8471,8715,1733,7685,6074,257,5249,4688,8549,5070,5366,2962,7031,6059,8861,9301,7328,6664,5294,8088,6500,6421,1518,4321,5336,2623,8742,1505,9941,1716,2820,4764,6783,906,2450,2857,7515,4051,7546,2416,9121,9264,1730,6152,1675,592,1805,9003,7256,7099,3444,3757,9872,4962,4430,1561,7586,3173,3066,3879,1241,2238,8643,8025,3144,7445,882,7012,1496,4780,9428,617,396,1159,3121,2072,1751,4926,7427,5359,8378,871,5468,8250,5834,9899,9811,9772,9424,2877,3651,7017,5116,8646,5042,4612,6092,2277,1624,7588,3409,1053,8206,3806,8564,7679,2230,6667,8958,6009,2026,7336,6881,3847,5586,9067,98,1750,8839,9522,4627,8842,2891,6095,7488,7934,708,3580,6563,8684,7521,9972,6089,2079,130,4653,9758,2360,1320,8716,8370,9699,6052,1603,3546,7991,670,3644,6093,9509,9518,7072,4703,2409,3168,2191,6695,228,2124,3258,5264,9645,9583,1354,1724,9713,2359,1482,8426,3680,6551,3148,9731,8955,4751,9629,6946,5421,9625,9391,1282,5495,6464,5985,4256,5984,4528,952,6212,6652,562,1476,6297,145,9182,8021,6211,1542,5856,4637,1574,2407,7785,1305,1362,2536,934,4661,4309,559,4052,1943,2406,516,4280,6662,2852,8808,7614,9064,1813,4529,6893,8110,4674,2427,2484,7237,3969,8340,1874,5543,7099,6011,3200,8461,8547,486,9474,9208,7397,9879,7503,9803,6747,1783,6466,9600,6944,432,8664,8757,4961,1909,6867,5988,4337,5703,3225,4658,4043,1452,6554,1142,7463,9754,5956,2363,241,1782,7923,7638,1661,5427,3794,8409,7210,260,8009,4154,692,3025,9263,2006,4935,2483,7994,5624,8186,7571,282,8582,9023,6836,6076,6487,6591,2032,8850,3184,3815,3125,7174,5476,8552,968,3885,2115,7580,8246,2621,4625,1272,1885,6631,6207,4368,4625,8183,2554,8548,8465,1136,7572,1654,7213,411,4597,5597,5613,7781,5764,8738,1307,7593,7291,8628,7830,9406,6208,6077,2027,833,7349,3912,7464,9908,4632,8441,8091,7187,6990,2908,4675,914,4562,8240,1325,9159,190,6938,3292,5954,2028,4600,9899,9319,3228,7730,5077,9436,159,7105,6622,7508,7369,4086,3768,2002,8880,8211,5541,2222,1119,216,3136,5682,4809,813,1193,4999,4103,4486,7305,6131,9086,7205,5451,2314,1287,528,8102,1446,3985,4724,5306,1355,5163,9074,9709,4043,7285,5250,2617,4756,1818,2105,6790,6627,2918,7984,7978,7021,2470,1636,3152,7908,8841,4955,222,6480,5484,4676,7926,5821,9401,3232,7176,916,8658,3237,1311,5943,8487,3928,7051,306,6033,3842,3285,8951,1826,7616,2324,648,9252,5476,8556,4445,6784};
        //int [] test = new int[]{-1, 2147483647};
        //int[] test = new int[]{0, 4, 0, 25, 12, 15, 14, 18};
        //int[] test = new int[]{0, 10, 0, 32, 25, 35};
        //int[] test = new int[]{0, 4, 1, 9, 0, 3, 1, 4};
        int[] test = new int[]{1,3};

        Solution sol = new Solution();
        System.out.println(sol.maxProfit(test));
        System.out.println(sol.maxProfit(0,test));
        System.out.println(sol.maxProfitn2(test));


    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        return maxProfit(prices, 2);
        //return maxProfitn2(prices);
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;
        return maxProfit( prices,k);
    }

    public void printExtremas(ArrayList<Profit> profits) {
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
        //printExtremas(profits);
        if (profits.isEmpty()) return 0;
        else {
            ArrayList<RefinedResult> results = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                results.add(new RefinedResult(i + 1));
            }
            if (k >= prices.length){
                int sum = profits.stream().map(x -> x.profit).reduce( (a,b) -> a + b).get();
                return sum;
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
        return profits;
    }
}
