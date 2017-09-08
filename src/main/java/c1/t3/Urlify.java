package c1.t3;

public class Urlify {

    public static void main(String [] args){
        String in = "Mr John Smith    ";
        char[] arr = in.toCharArray();
        Urlify url = new Urlify();
        url.urlify(arr,13);
        String trans = new String(arr);
        System.out.println(trans);
    }

    void urlify(char [] in, int truelength){
        int spacecount = 0;
        for (int j = 0; j < truelength; j++){
            char c = in[j];
            if (c == ' ')
                spacecount++;
        }
        if (spacecount == 0)
            return;
        int i = truelength-1;
        for( int j = truelength-1 + (spacecount*2); j>=0; j--){
            if (in[i] == ' ')
            {
                in[j] = '0';
                in[j-1] = '2';
                in[j-2] = '%';
                j = j - 2;
            } else {
                in[j] = in[i];
            }
            i--;
        }

    }
}
