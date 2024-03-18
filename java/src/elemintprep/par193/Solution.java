package elemintprep.par193;


import java.util.ArrayList;

public class Solution {

  public static void main(String[] args) throws InterruptedException{
    OddEvenMonitor monitor = new OddEvenMonitor();
    Thread t1 = new EvenThread(monitor);
    Thread t2 = new UnEvenThread(monitor);

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    ArrayList<Integer> al = new ArrayList<>();
    al.add(1);
    al.add(2);

    al.remove(al.size()-1);
    String s = "sdalfj";
    s.length();

  }
}
