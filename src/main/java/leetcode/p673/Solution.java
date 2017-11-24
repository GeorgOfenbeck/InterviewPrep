package leetcode.p673;

import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] arr = {1,5,3};
    //int[] arr = {1,3,5,4,7};
    int res = s.findNumberOfLIS(arr);
    System.out.println(res);

  }

  class Node{
    Node next = null;
    Integer value = 0;
    Integer length = 1;
    Node(Integer pval){
      value = pval;
    }
    void setNext(Node pnext){
      next = pnext;
      length = length + next.length;
    }
  }


  public void updateLists(TreeMap<Integer, LinkedList<Node>> maxvals, TreeMap<Integer, LinkedList<Node>> lens, Integer cur){

    LinkedList<Node> ll = new LinkedList<>();
    Iterator it = maxvals.entrySet().iterator();

    //pre existing chains
    while (it.hasNext()){
      Map.Entry<Integer, LinkedList<Node>> entry = (Map.Entry<Integer, LinkedList<Node>> ) it.next();
      if (entry.getKey() >= cur) break; else {
        LinkedList<Node> toupdate = entry.getValue();
        toupdate.forEach(p -> {
          Node node = new Node(cur);
          node.setNext(p);
          ll.add(node);
        });
      }
    }
    Node node = new Node(cur);
    ll.add(node); //1 size node


    ll.forEach(p -> {
      if (maxvals.containsKey(p.value)){
        LinkedList<Node> max = maxvals.get(p.value);
        max.add(p);
      } else{
        LinkedList<Node> max = new LinkedList<>();
        max.add(p);
        maxvals.put(p.value, max);
      }
    });
    ll.forEach(p -> {
      if (lens.containsKey(p.length)){
        LinkedList<Node> max = lens.get(p.length);
        max.add(p);
      } else{
        LinkedList<Node> max = new LinkedList<>();
        max.add(p);
        lens.put(p.length, max);
      }
    });

  }

  public int findNumberOfLIS(int[] nums) {
    if (nums == null) return 0;
    if (nums.length == 1) return 1;
    TreeMap<Integer, LinkedList<Node>> maxvals = new TreeMap<>();
    TreeMap<Integer, LinkedList<Node>> lens = new TreeMap<>(Collections.reverseOrder());


    int[] longest = new int[nums.length];
    int[] nrpaths = new int[nums.length];


    int totalmax = 0;
    nrpaths[0] = 1;
    longest[0] = 1;

    for (int i = 1; i < nums.length; i++){
      int curnum = nums[i] ;
      //updateLists(maxvals,lens,curnum);
      int curmax = 0;
      int nrmax = 1;
      for (int j = 0; j < i; j++ ){
        int consid = nums[j];
        if (consid < curnum) {
          if (longest[j] == curmax)
            nrmax = nrmax + nrpaths[j];
          if (longest[j] > curmax){
            curmax = longest[j];
            nrmax = nrpaths[j];
          }
        }
      }
      nrpaths[i] = nrmax;
      longest[i] = curmax + 1;
      if (curmax+1 > totalmax)
        totalmax = curmax+1;
    }

    int sumpaths = 0;
    for (int i = 0; i < nums.length; i++){
      if (longest[i] == totalmax) {
        sumpaths = sumpaths + nrpaths[i];
      }
    }
    return sumpaths;
    //return lens.firstEntry().getValue().size();
  }
}
