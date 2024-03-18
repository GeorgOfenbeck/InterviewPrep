package elementsjava.bitstuff;

public class Parity2 {
    public static short parity(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 0x1);
    }


    public static short parity2(long x) {
        return (short) 0;
    }

    public static void main(String[] args) {
        int x = 0b11;
        long blub = -0;
        System.out.println(Long.toBinaryString(propagate(blub)));
    }

    public static long propagate(long y) {
        int x =        0b101000;
        int xm1=       0b100111;
        int neg =      0b011000;
        int and =      0b001000;
        int expected = 0b101111;

        long leftmost = y & ~(y-1);
        long mask =  (leftmost -1);
        return y ^ mask;

    }


    public static long swap(long y, int i, int j){
        if (i == j) return y;

        long mask = ~0 ^ 0;
        long ival = (y >>> i) | mask;
        long jval = y >>> j | mask;

        y = y | (ival << j);
        y = y | (jval << i);

        return y;
    }

}