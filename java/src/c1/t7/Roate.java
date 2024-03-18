package c1.t7;



class Pixel{};

public class Roate {


    public void rotate (Pixel[][] matrix, int length){
        Pixel[] row = matrix[0];

        for (int i = 0; i < length-1; i++)
        {
            Pixel x1,x2,x3,x4;
            x1 = matrix[0][0 + i];
            x2 = matrix[length-1][0 + i];
            x3 = matrix[length-1][length-1 - i];
            x4 = matrix[ length-1 - i] [0];

            matrix[0][0+ i] = x4;
            matrix[length-1][0 + i] = x1;
            matrix[length-1][length-1 - i] = x2;
            matrix[ length-1 - i] [0] = x3;

            //continue inwards
        }
    }
}
