package leetcode.p691;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.*;

class Solution {

    public static void main(String[] args) {
        String[] stickers = {"with","example","science"};
        String target = "thehat";
        Solution sol = new Solution();
        Integer res = sol.minStickers(stickers,target);
        System.out.println(res);
    }


    public void recursive(String[] stickers, String target){

        Character cur = target.charAt(0);

        

    }


    public static HashMap<Character,Integer> S2C(String inital){
        HashMap<Character,Integer> chars = new HashMap<>();
        inital.chars().forEach(ci ->{
            Character c = (char)ci;
            if (chars.containsKey((char)c)){
                Integer prev = chars.get(c);
                chars.put((char)c,prev+1);
            }
            else
                chars.put((char)c,1);
        });
        return chars;
    }

    public class Entry{
        StickT retstick;
        Integer nr;
        Integer sofar;
        Entry(StickT ps, Integer pnr){
            retstick = ps;
            nr = pnr;
        }
    }

    public class StickT{
        HashMap<Character,Integer> chars = null;
        HashMap<Character,Integer> state = new HashMap<>();
        StickT(HashMap<Character,Integer> pchars){
            chars = pchars;
            chars.forEach((k,v)-> state.put(k,0)); //we assume empty for first go
        }
        public void refresh(){
            HashMap<Character,Integer> state = new HashMap<>();
            chars.forEach((k,v)-> state.put(k,v));
        }

        public StickT cloneme(){
            StickT cl = new StickT(chars);
            state.forEach((k,v)-> cl.state.put(k,v));
            return cl;
        }

        public Entry take(Character req){
            StickT ret = cloneme();
            Integer res = ret.takeupdate(req);
            Entry ent =  new Entry(ret,res);
            return ent;
        }

        public Integer takeupdate(Character req){
            if (state.containsKey(req)){
                Integer rem = state.get(req);
                if (rem == 0){
                    refresh();
                    state.put(req,chars.get(req) - 1);
                    return 1;
                } else {
                    state.put(req,rem-1);
                    return 0;
                }
            } else {
                return -1;
            }
        }



    }

    public int minStickers(String[] stickers, String target) {
        //first check if possible

        HashSet<Character> avail = new HashSet<>();
        Arrays.stream(stickers).forEach(s -> {
            s.chars().forEach(c -> avail.add((char)c));
        });
        //early abort
        for (int i= 0; i<target.length(); i ++){
            Character t = target.charAt(i);
            if (!avail.contains(t))
                return -1;
        }


        ArrayList<StickT> stickts = new ArrayList<>();
        Arrays.stream(stickers).forEach(st -> {
            stickts.add(new StickT(S2C(st)));
        });

        ArrayList<ArrayList<Entry>> table = new ArrayList<ArrayList<Entry>>();

        ArrayList<Entry> ini = new ArrayList<Entry>();
        stickts.forEach(st -> {
            Entry ent = new Entry(st,0);
            ent.sofar = 0;
            ini.add(ent);
        });
        table.add(ini);

        target.chars().forEach(c -> table.add(new ArrayList<Entry>()));

        for (int i = 1; i < table.size(); i++){
            Character cur = target.charAt(i-1);

            for (int j = 0; j < stickts.size(); j++){
                TreeMap<Integer,Entry> options = new TreeMap<>();

                for (int k = 0; k < stickts.size(); k++){
                    Entry prev = table.get(i-1).get(k);
                    if (prev.sofar == Integer.MAX_VALUE){
                        options.put(prev.sofar,prev);
                    } else {
                        Entry now = prev.retstick.take(cur);
                        if (now.nr == -1)
                            now.sofar = Integer.MAX_VALUE;
                        else
                            now.sofar = prev.sofar + now.nr;
                        options.put(now.sofar,now);
                    }
                }
                Entry smallest = options.get(options.firstKey());

                ArrayList row = table.get(i);
                row.add(smallest);
            }
        }
        ArrayList lastrow = table.get(table.size()-1);
        Entry lastentry =  (Entry) lastrow.get(lastrow.size()-1);

        return lastentry.sofar;




    }
}