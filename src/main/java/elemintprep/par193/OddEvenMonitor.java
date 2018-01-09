package elemintprep.par193;

public class OddEvenMonitor {

  public static final boolean ODD_TURN = true;
  public static final boolean EVEN_TURN = false;
  private boolean turn = ODD_TURN;

  public synchronized void waitTurn(boolean oldTurn){
    while (turn != oldTurn){
      try{
        wait();
      } catch (InterruptedException e){
        System.out.println();
      }
    }
  }

  public synchronized void toggleTurn(){
    turn = !turn;
    notify();
  }

}
