package c5.t8;

public class Horizontal {

    static void drawLine(byte [] screen, int width, int x1, int x2, int y){
        int height = screen.length / width;

        int start = y * width + x1 / 8;

        int end = y * width + x2 / 8;

        for (int i = start + 1; i < end -1; i++){
            screen[i] = Byte.MIN_VALUE;
        }
        byte tmp = 0;
        for (int i = 0; i < x1%8; i++)
            tmp |= (1 << i);
        screen[start] = tmp;

        tmp = 0;

        for (int i = 0; i < (x2%8); i++)
            tmp |= (1 << 7-i);
        byte bla = Byte.MIN_VALUE;



        screen[end] = tmp;


    }
}
