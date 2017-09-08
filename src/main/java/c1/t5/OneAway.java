package c1.t5;

public class OneAway {

    public static void main(String[] args) {
        boolean b1 = oneway("pale", "ple");
        boolean b2 = oneway("pales", "pale");
        boolean b3 = oneway("pale", "bale");
        boolean b4 = oneway("pale","bake");
        System.out.print(3);
    }

    public static boolean oneway(String s1, String s2) {
        String a1, a2;

        if (s1.length() < s2.length()) {
            a1 = s1;
            a2 = s2;
        } else {
            a1 = s2;
            a2 = s1;
        }

        boolean skip = true;
        int j = 0;
        for (int i = 0; i < a1.length(); i++) {

            if (a1.charAt(i) != a2.charAt(j))
                if (skip) {
                    if (a1.length() != a2.length())
                        j++;
                    skip = false;
                } else return false;

            j++;
        }
        return true;
    }
}
