package leetcode.p359;

import java.util.*;


public class  RemoveEldest {

    public Map<String, Integer> map;
    int lastSecond = 0;

    /** Initialize your data structure here. */
    public RemoveEldest() {
        map = new java.util.LinkedHashMap<String, Integer>(100, 0.6f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return lastSecond - eldest.getValue() > 10;
            }
        };
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        lastSecond = timestamp;
        if(!map.containsKey(message)||timestamp - map.get(message) >= 10){
            map.put(message,timestamp);
            return true;
        }
        return false;
    }
}