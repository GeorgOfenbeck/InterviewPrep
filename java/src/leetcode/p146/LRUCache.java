package leetcode.p146;

import java.util.*;

class LRUCache {

  public static void main(String[] args) {
    LRUCache c = new LRUCache(2);
    c.get(2);
    c.put(2, 6);
    c.get(1);
    c.put(1, 5);
    c.put(1, 2);
    c.get(1);
    c.get(2);

  }

  LinkedHashMap<Integer, Integer> cache = null;
  int full;

  public LRUCache(int capacity) {
    cache = new LinkedHashMap<>(capacity);
    full = capacity;
  }

  public int get(int key) {
    if (cache.containsKey(key)) {
      int value = cache.get(key);
      cache.remove(key);
      cache.put(key, value);
      return value;
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    if (cache.containsKey(key)) {
      cache.remove(key);
      cache.put(key, value);
    } else {
      if (cache.size() == full) {
        int oldestkey = cache.keySet().iterator().next();
        cache.remove(oldestkey);
        cache.put(key, value);
      } else {
        cache.put(key, value);
      }
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj = new
 * LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
