package elemintprep.par193;


public class UnEvenThread extends Thread{
  private final OddEvenMonitor monitor;

  public UnEvenThread(OddEvenMonitor monitor){
    this.monitor = monitor;
  }

  public void run(){
    for (int i = 1; i <= 100; i+= 2){
      monitor.waitTurn(OddEvenMonitor.ODD_TURN);
      System.out.println(i);
      monitor.toggleTurn();
    }
  }
}