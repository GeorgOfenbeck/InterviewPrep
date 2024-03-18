package c1.t6;

public class Compression {


    public String compress(String in) {
        StringBuilder str = new StringBuilder();
        if (in.length() == 0) return in;

        char last = ' ';
        int count = 0;

        for (int i = 1; i < in.length(); i++) {
            if (in.charAt(i) == last) {
                count ++;
            } else {
                str.append(count);
                count = 0;
                str.append(in.charAt(i));
            }
        }
        if (count > 0 )
            str.append(count);

        return str.toString();
    }
}
