package dynprog.zerooneKnapsack;

import java.util.*;

class Item implements Comparable<Item> {

  Integer value;
  Integer weight;

  public int compareTo(Item other) {
    if (this.weight > other.weight) {
      return -1;
    } else {
      if (this.weight < other.weight) {
        return 1;
      } else {
        if (this.value < other.value) {
          return -1;
        } else {
          if (this.value > other.value) {
            return 1;
          } else {
            return 0;
          }
        }
      }
    }
  }


  Item( Integer pweight, Integer pvalue) {
    value = pvalue;
    weight = pweight;
  }
}

class Key{
  Integer remain;
  Integer items;
  Key(Integer premain, Integer pitems){
    remain = premain;
    items= pitems;
  }

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Key key = (Key) o;

    if (remain != key.remain) return false;
    else return items == key.items;
  }

  @Override
  public int hashCode(){
    int result = remain;
    result = 31 * result + items;
    return result;
  }
}

public class Solution {



  HashMap<Key,Integer> cache = new HashMap<>();
  public static void main(String[] args) {
    TreeSet<Item> items = new TreeSet<>();
    items.add(new Item(1, 1));
    items.add(new Item(3, 4));
    items.add(new Item(4, 5));
    items.add(new Item(5, 7));
    Solution sol = new Solution();
    Integer total = sol.helper(items, 7);
    System.out.println(total);
  }

  private TreeSet<Item> copy(TreeSet<Item> items) {
    TreeSet<Item> cpy = new TreeSet<>();
    items.forEach(p -> cpy.add(p));
    return cpy;
  }

  public Integer helper(TreeSet<Item> items, Integer total) {
    if (items.isEmpty()) {
      return 0;
    } else {
      Key key = new Key(total,items.size());

      if (cache.containsKey(key)){
        System.out.println("Cache hit");
        return cache.get(key);
      } else {
        Item first = items.first();
        TreeSet<Item> rest = copy(items);
        rest.remove(first);

        Integer pickfirst;
        if (total - first.weight >= 0)
          pickfirst = first.value + helper(rest, total - first.weight);
        else
          pickfirst = 0;
        Integer nopickfirst = helper(rest, total);
        Integer max = Math.max(pickfirst, nopickfirst);
        cache.put(key, max);
        return max;
      }
    }
  }

}
