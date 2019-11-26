package leetcode.p038;

class Solution {

    public static void main (String [] args){
        Solution sol = new Solution();
        for (int i = 1; i<10; i++)
            System.out.println(sol.countAndSay(i));
    }

    public String countAndSay(int n) {
        int init = 1;

        String res = "" + init;
        for (int i = 1; i< n; i++){
            res = count(res);
        }
        return res;
    }


    String count(String in){
        StringBuilder sb = new StringBuilder();

        Character cur = in.charAt(0);
        int count = 1;
        for (int i = 1; i < in.length(); i++){
            Character focus = in.charAt(i);
            if (focus.equals(cur))
                count ++;
            else {
                String app = count + "" + cur;
                sb.append(app);
                count = 1;
                cur = focus;
            }
        }
        String app = count + "" + cur;
        sb.append(app);
        return sb.toString();
    }
}
