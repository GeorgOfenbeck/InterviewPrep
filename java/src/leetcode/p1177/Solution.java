package leetcode.p1177;
/* 
import java.util.*;
import java.util.stream.Collectors;
import com.google.common.collect.*;

class Solution {

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] queries = {{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}};
        sol.canMakePaliQueries("abcda", queries );
    }

    ArrayList<ImmutableMap<Character,Integer>> toCountMap(String s) {
        ArrayList<ImmutableMap<Character, Integer>> res = new ArrayList<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (i == 0) {
                ImmutableMap<Character, Integer> state = ImmutableMap.<Character, Integer>builder().put(c, 1).build();
                res.add(state);
            } else {
                ImmutableMap<Character, Integer> prev = res.get(i-1);
                ImmutableMap<Character, Integer> state;
                if (prev.containsKey(c)) {
                    ImmutableMap.Builder<Character,Integer> builder = ImmutableMap.<Character, Integer>builder();
                    for (ImmutableMap.Entry<Character,Integer> e : prev.entrySet()){
                        if (e.getKey() == c){
                            builder.put(c, prev.get(c) + 1);
                        } else
                            builder.put(e.getKey(),e.getValue());
                    }
                    state = builder.build();
                }
                else {
                    state = ImmutableMap.<Character, Integer>builder().putAll(prev).put(c, 1).build();
                }
                res.add(state);
            }
        }
        return res;
    }

    ImmutableMap<Character,Integer> makeDiff(ImmutableMap<Character,Integer> a, ImmutableMap<Character,Integer> b){
        if (a == b){
            return ImmutableMap.<Character,Integer>builder().build();
        } else {
            //HashMap<Character,Integer> res = new HashMap<>();
            ImmutableMap.Builder<Character,Integer> res = ImmutableMap.<Character,Integer>builder();
            for ( Map.Entry<Character,Integer> entry : b.entrySet()){
                Character key = entry.getKey();
                if (a.containsKey(key))
                    res.put(key,entry.getValue() - a.get(key));
                else
                    res.put(key, entry.getValue());
            }
            return res.build();
        }
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        ArrayList<ImmutableMap<Character,Integer>> stateAtPos = toCountMap(s);

        List<Boolean> answers = new LinkedList<>();
        for (int [] query : queries){
            int start = query[0];
            int stop = query[1]; //inclusive
            int k = query[2];

            ImmutableMap<Character,Integer> diff;
            if (start == 0){
                diff = stateAtPos.get(stop);
            } else
                diff = makeDiff(stateAtPos.get(start-1),stateAtPos.get(stop));

            int nrfixes = 0;
            int nrunpaired = 0;
            for (Map.Entry<Character,Integer> e : diff.entrySet()){
                if (!Character.isLowerCase(e.getKey()) ){ //needs to be fixed
                    nrfixes = nrfixes + e.getValue();
                }
                if (Character.isLowerCase(e.getKey()) && e.getValue() % 2 != 0){
                    nrunpaired = nrunpaired + 1;
                }
            }

            int reqfixes = nrunpaired/2;

            if (reqfixes + nrfixes > k)
                answers.add(false);
            else
                answers.add(true);
        }

        return answers;
    }
} 
*/