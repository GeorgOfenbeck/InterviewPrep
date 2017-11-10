package c5.t1;

public class Inseration2 {


  public static void main(String[] args){
    Integer x = 1;
    MyInt y = new MyInt(1);
    Quicktest(x);
    Quicktest2(y);
    System.out.println(x);
    System.out.println(y.member);

    int n = 5;
      int pattern = 0b10101010101010101010101010101010;
      int antipattern = ~pattern;


      int i = n | n >> 1;
      i |= i >> 2;
      i |= i >> 4;
      i |= i >> 8;
      i |= i >> 16;

      if( ( n == (pattern & i) ) ||
          ( n == (antipattern & i) )
          ) System.out.println(true);
      else System.out.println(false);

  }

  public static void Quicktest(Integer t){
    t = 0;
  }



  public static void Quicktest2(MyInt t){
    t.member = 0;
  }

  public static Integer insert(Integer N, Integer M, Integer i, Integer j) {
    if (N == null || M == null || j < i ) {
      return null;
    } else {
      if (i == j) {
        return N;
      } else {
        Integer result = 0;
        Integer mask = 0;
        //shift M
        M = M << j;

        for (int k = j; k < i; k++){
          mask |= 1 << k;
        }


        result = M & mask; //clean non mask M
        N = ~mask & N; //clean mask N
        result = N | result;
        return result;
      }
    }

  }

}
