package c8.t1;

public class TripleChild {

    public static void main(String[] args) {
        Integer blub = Steps(50);
        System.out.println(blub);

    }

    public static Integer Steps(Integer n){
        int[] arr = new int[n+1];
        return Steps_x(n,  arr);
    }

    public static Integer Steps_x(Integer n, int [] cache)  {

      /*  if (cache[n] != 0)
            return cache[n];*/

        if (n == 0) {
            cache[0] = 0;
            return 0;
        }
        else if (n == 1) {
            cache[1] = 1;
            return 1;
        }
        else if (n == 2) {
            cache[2] = 2;
            return 2;
        }
        else if (n == 3) {
            Integer pos = 0;
            pos = Steps_x(0, cache) + 1;
            pos += 2; //2 + 1
            pos += 1;// 3 *1
            cache[3] = pos;
            return pos;
        }
        else
        if (n > 3){
            Integer pos = 0;
            pos += Steps_x(n-1, cache);
            pos += Steps_x(n-2, cache);
            pos += Steps_x(n-3, cache);
            cache[n] = pos;
            return pos;
        }
        else {
            assert(n >= 0);
            return -1;
        }
    }
}
