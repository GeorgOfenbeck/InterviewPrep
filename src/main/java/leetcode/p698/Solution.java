package leetcode.p698;

import java.util.*;
import java.util.Map.Entry;


public class Solution {

  HashMap<Integer,Integer> cache = new HashMap<>();

  public static void main(String[] args){
    int [] nums = {1,1,1,1,1,1,1,1,1,1};
    int k = 5;
    Solution sol = new Solution();
    System.out.println(sol.canPartitionKSubsets(nums,k));
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = Arrays.stream(nums).sum();
    if (sum%k != 0) return false; //the sum cannot be divided by k
    int frac = sum /k;

    TreeMap<Integer,TreeSet<Integer>> tm = new TreeMap<>();

    for (int i = 0; i < nums.length; i++){
      int p = nums[i];
      if (tm.containsKey(p)){
        TreeSet<Integer> sofar = tm.get(p);
        sofar.add(i);
        tm.put(p,sofar);
      } else {
        TreeSet<Integer> ll = new TreeSet<>();
        ll.add(i);
        tm.put(p,ll);
      }
    };
    int key = 0;
    for (int i = 0; i < nums.length; i++){
      key = key | (1 << i);
    }

    ValueStore ini = new ValueStore(tm,key,nums);
    Integer sofar = ini.largestRemainingValue();
    return recurse(sofar, ini.takeLargestRemaining(), frac);

  }

  class ValueStore {
    private TreeMap<Integer, TreeSet<Integer>> tm; //always gets updated (only remaining value)
    private Integer key; //relates to nums
    private int nums[];

    ValueStore(TreeMap<Integer, TreeSet<Integer>> ptm, Integer pkey, int[] pnums){
      tm = ptm;
      key = pkey;
      nums = pnums;
    }


    public TreeMap<Integer, TreeSet<Integer>> deepcopy(TreeMap<Integer, TreeSet<Integer>> tocopy){
      TreeMap<Integer, TreeSet<Integer>> copy = new TreeMap<>();
      tocopy.forEach( (k,v) -> copy.put(k,deepcopy(v)));
      return copy;
    }

    public TreeSet<Integer> deepcopy(TreeSet<Integer> tocopy){
      TreeSet<Integer> ll = new TreeSet<>();
      tocopy.stream().forEach(p -> ll.add(p));
      return ll;
    }

    public SortedMap<Integer, TreeSet<Integer>> options(Integer max){
       return tm.headMap(max,true);
    }

    public Integer largestRemainingValue(){
      Entry<Integer, TreeSet<Integer>> entry = tm.lastEntry();
      return entry.getKey();
    }

    public ValueStore takeValue(Integer value, Integer index){
      TreeMap<Integer, TreeSet<Integer>> copy = deepcopy(tm);       
      TreeSet<Integer> ll = copy.get(value);
      ll.remove(index);
      Integer newkey = key & (~(1 << index));
      if (ll.size() > 0){
        return new ValueStore(copy,newkey,nums);
      } else {
        copy.remove(value);
        return new ValueStore(copy,newkey,nums);
      }
    }
    
    public ValueStore takeLargestRemaining(){
      TreeMap<Integer, TreeSet<Integer>> copy = deepcopy(tm);
      Entry<Integer, TreeSet<Integer>> entry = copy.lastEntry();
      TreeSet<Integer> ll = entry.getValue();
      int index = ll.last();
      ll.remove(index);
      Integer newkey = key & (~(1 << index));
      if (ll.size() > 0){
        return new ValueStore(copy,newkey,nums);
      } else {
        copy.remove(entry.getKey());
        return new ValueStore(copy,newkey,nums);
      }
    }
    public boolean isEmpty(){
      return tm.isEmpty();
    }
  }

  //k == 4, lenght = 6
  public boolean recurse(Integer sofar, ValueStore vs, int sum){

    int remain = sum - sofar;
    if (remain == 0){ //we fixed a subset
      cache.put(vs.key,1);
      if (vs.isEmpty()) return true;
      Integer nsofar = vs.largestRemainingValue();
      ValueStore nvs = vs.takeLargestRemaining();
      return recurse(nsofar,nvs,sum);
    } else {
      //return me the options
      SortedMap<Integer, TreeSet<Integer>> options = vs.options(remain);
      if (options.isEmpty()) return false;

      Iterator<Entry<Integer,TreeSet<Integer>>> it = options.entrySet().iterator();
      Boolean result = false;
      while( it.hasNext() && !result){
        Entry<Integer,TreeSet<Integer>> entry = it.next();
        Integer k = entry.getKey();
        TreeSet<Integer> v = entry.getValue();
        Iterator<Integer> it2 = v.descendingIterator();
        while (it2.hasNext() && !result){
          Integer index = it2.next();
          ValueStore nvs = vs.takeValue(k,index);
          // return option numeric val
          // update VS with option
          result = recurse(sofar + k, nvs,sum);
        }
      }
      return result;
    }
  }
}
