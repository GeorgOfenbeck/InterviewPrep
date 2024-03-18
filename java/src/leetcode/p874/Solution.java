package leetcode.p874;

import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean test(){
        Pair p1 = new Pair(1,3);
        Pair p2 = new Pair(1,3);
        boolean check = p1.equals(p2);
        HashSet<Pair> set = new HashSet<>();
        set.add(p1);
        boolean check2 = set.contains(p1);
        boolean check3 = set.contains(p2);
        return check;
    }

    public static void main(String[] args ){
        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};

        Solution sol = new Solution();
        sol.test();

        int res = sol.robotSim(commands,obstacles);
        System.out.println(res);
    }



    class Pair  implements Comparable<Pair> {
        int x;
        int y;
        Pair (int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.x == o.x && this.y == this.y)
                return 0;
            else
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair))
                return false;

            Pair o = (Pair) obj;

            if (this.x == o.x && this.y == this.y)
            {
                return true;
            } else return false;
        }

        @Override
        public int hashCode() {
            return this.x * 10000 + this.y;
        }



    }
    class PairwDirection{
        Pair pair;
        int direction;
        PairwDirection(Pair pair, int direction){
            this.pair = pair;
            this.direction = direction;
        }
    }


    public int robotSim(int[] commands, int[][] obstacles) {

        Set<Pair> obset = obstacles2Set(obstacles);
        PairwDirection current = new PairwDirection(new Pair(0,0),0);
        for (int i = 0; i < commands.length; i++){
            current = move(current,commands[i],obset);
        }

        return current.pair.x * current.pair.x + current.pair.y * current.pair.y;
    }

    private Set<Pair> obstacles2Set(int[][] obstacles){
        Set<Pair> obset = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++){
            obset.add(new Pair(obstacles[i][0],obstacles[i][1]));
        }
        return obset;
    }

    //0 up, 1 right, 2 down, 3 right
    /*
    -2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
     */
    private PairwDirection move(PairwDirection curr, int command, Set<Pair> obstacles){
        PairwDirection after = new PairwDirection(curr.pair,curr.direction);
        if (command == -1){
            after.direction = after.direction + 1;
            if (after.direction == 4) after.direction = 0;
            return after;
        } else {
            if (command == -2){
                after.direction = after.direction + -1;
                if (after.direction == -1) after.direction = 3;
                return after;
            } else {
                //error handling
                int x = 0;
                int y = 0;
                if (curr.direction == 0){
                    y = 1;
                }
                if (curr.direction == 2){
                    y = -1;
                }
                if (curr.direction == 1){
                    x = 1;
                }
                if (curr.direction == 3){
                    x = -1;
                }

                for (int i = 0; i< command; i ++){
                    Pair movetry = new Pair(after.pair.x, after.pair.y);
                    movetry.x = movetry.x + x;
                    movetry.y = movetry.y + y;
                    if (obstacles.contains(movetry)){
                        i = command;
                    } else {
                        after.pair = movetry;
                    }
                }
                return after;
            }
        }
    }
}