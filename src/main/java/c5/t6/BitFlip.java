package c5.t6;

public class BitFlip {

    public static Integer bitflip(Integer a, Integer b){
        return Integer.bitCount(a ^ b);
    }
}
