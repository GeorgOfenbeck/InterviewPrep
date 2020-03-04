package leetcode.p359;

public class Solution {


    public static void main(String [] args){
        Logger logger = new Logger();

// logging string "foo" at timestamp 1
        logger.shouldPrintMessage(1, "foo");

// logging string "bar" at timestamp 2
        logger.shouldPrintMessage(2,"bar"); //returns true;

// logging string "foo" at timestamp 3
        logger.shouldPrintMessage(3,"foo");// returns false;

// logging string "bar" at timestamp 8
        logger.shouldPrintMessage(8,"bar"); //returns false;

// logging string "foo" at timestamp 10
        logger.shouldPrintMessage(10,"foo");// returns false;

// logging string "foo" at timestamp 11
        logger.shouldPrintMessage(11,"foo");// returns true;
    }

}
