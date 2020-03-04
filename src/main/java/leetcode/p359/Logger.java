package leetcode.p359;

import java.util.*;

class Tmp{
    OnlyTen newhead;
    boolean res;
    Tmp(OnlyTen newhead, boolean res){
        this.newhead = newhead;
        this.res = res;
    }

}

class OnlyTen{
    Set<String> buffer;
    int ts;
    OnlyTen next;
    OnlyTen(int ts, OnlyTen next){
        this.ts = ts;
        this.next = next;
        buffer = new HashSet<>();
    }

    boolean print(String s, int ts){
        if (buffer.contains(s)) return false;
        else {
            if (next == null) {
                return true;
            } else {
                return next.purge(ts,s);
            }

        }
    }

    boolean toOld(int ts){
        //first check if we should print
        int diff = ts - this.ts;
        return diff >= 10;
    }

    Boolean purge(int ts, String s){
        if (next != null && next.toOld(ts)){
            next = null; //drop the older then 10 s msgs
        }
        return this.print(s,ts);
    }

    Tmp printPurge(int ts, String s){
        OnlyTen newhead = this;
        boolean willprint = false;
        if (ts > this.ts){
            newhead = new OnlyTen(ts,this);
            willprint = newhead.purge(ts,s);
        } else {
            willprint = purge(ts,s);
        }
        if (willprint) newhead.buffer.add(s);
        return new Tmp(newhead,willprint);
    }

}

class Logger {

    OnlyTen head;
    /** Initialize your data structure here. */
    public Logger() {
        head = new OnlyTen(-1,null);
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Tmp check = head.printPurge(timestamp,message);
        head = check.newhead;
        return check.res;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */