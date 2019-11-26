package leetcode.p438;


import java.util.*;


class checkAnagram{
    HashMap<Character, Integer> hits = new HashMap<>();
    HashSet<Character> valid = new HashSet<>();
    HashSet<Character> flag = new HashSet<>();
    LinkedList<Character> window = new LinkedList<>();
    Integer running_idx = 0;
    Integer window_size;


    checkAnagram(String p){
        window_size = p.length();
        for (Character c : p.toCharArray()) {
            valid.add(c);
            flag.add(c);
            if (hits.containsKey(c)) {
                Integer sofar = hits.get(c);
                hits.put(c, sofar -1);
            } else {
                hits.put(c, -1);
            }
        }
    }

    boolean addChar(Character c){
        running_idx++;
        window.add(c);
        if (window.size() > window_size){
            Character dropped = window.removeFirst();
            if (valid.contains(dropped)) {
                Integer sofar = hits.get(dropped);
                hits.put(dropped, sofar - 1);
                if (hits.get(dropped) == 0)
                    flag.remove(dropped);
                else
                    flag.add(dropped);
            }
        }
        if (valid.contains(c)) {
            hits.put(c, hits.get(c) + 1);
            if (hits.get(c) == 0)
                flag.remove(c);
            else
                flag.add(c);
            if (flag.isEmpty()) return true;
            else return false;
        }else {
            return false;
        }
    }

    void printStatus(){
        System.out.println("Window size " + window_size + " window: " + window + " hits: " + hits + " flag: " +flag );
    }




}

class Solution {

    public static void main(String[] args) {
        String a = "cbaebabacd";
        String p = "abc";
        Solution sol = new Solution();
        List<Integer> x = sol.findAnagrams(a,p);
        System.out.println(x);

    }


    public List<Integer> findAnagrams(String s, String p) {
        LinkedList<Integer> result = new LinkedList<>();
        checkAnagram check = new checkAnagram(p);
        for (Character c: s.toCharArray()) {
            if (check.addChar(c))
                result.addLast(check.running_idx-check.window_size);
            //check.printStatus();
        }
        return result;
    }
}

class IsAnagram {
    HashMap<Character, Integer> hits;
    HashSet<Character> valid;
    String anagram;

    IsAnagram(String p) {
        anagram = p;
        hits = new HashMap<>();
        valid = new HashSet<>();
        for (Character c : p.toCharArray()) {
            valid.add(c);
            if (hits.containsKey(c)) {
                Integer sofar = hits.get(c);
                hits.put(c, sofar + 1);
            } else {
                hits.put(c, 1);
            }
        }
    }

    boolean check() {
        if (hits.isEmpty()) return true;
        else return false;
    }


    boolean addChar(Character c, List<Integer> res, Integer pos) {
        if (valid.contains(c)) {
            if (hits.containsKey(c)) {
                Integer sofar = hits.get(c);
                if (sofar == 1) {
                    hits.remove(c);
                    if (check()) {
                        res.add(pos);
                        return false;
                    } else return true;
                } else {
                    hits.put(c, sofar - 1);
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}

