package parallel;

public class Calculator implements Runnable{

  public static void main(String[] args){
    for (int i = 1; i <= 10; i++){
      Calculator cal = new Calculator(i);
      Thread thread = new Thread(cal);
      thread.start();
    }
  }

  private int number;

  public Calculator(int number){
    this.number=number;
  }

  @Override
  public void run(){
    for (int i = 0; i<= 10; i++){
      System.out.printf("%s$ %s %d * %d = %d\n", Thread.currentThread().getName(), Thread.currentThread().getState().toString(),number,i,i*number);
    }
  }

}
