package leetcode.p017;

import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> combinations = new LinkedList<>();
        recurse(combinations,"",0,digits);
        return combinations;
    }

    void recurse(LinkedList<String> sofar, String cur, int pos, String digits){
        if (pos == digits.length()){//we parsed the full string
            StringBuilder sb = new StringBuilder();
            sofar.add(cur);
        } else {
            Character focus = digits.charAt(pos);
            switch (focus) {
                case '2':
                    recurse(sofar,cur + 'a',pos + 1,digits);
                    recurse(sofar,cur + 'b',pos + 1,digits);
                    recurse(sofar,cur + 'c',pos + 1,digits);
                    break;
                case '3':
                    recurse(sofar,cur + 'd',pos + 1,digits);
                    recurse(sofar,cur + 'e',pos + 1,digits);
                    recurse(sofar,cur + 'f',pos + 1,digits);
                    break;
                case '4':
                    recurse(sofar,cur + 'g',pos + 1,digits);
                    recurse(sofar,cur + 'h',pos + 1,digits);
                    recurse(sofar,cur + 'i',pos + 1,digits);
                    break;
                case '5':
                    recurse(sofar,cur + 'j',pos + 1,digits);
                    recurse(sofar,cur + 'k',pos + 1,digits);
                    recurse(sofar,cur + 'l',pos + 1,digits);
                    break;
                case '6':
                    recurse(sofar,cur + 'm',pos + 1,digits);
                    recurse(sofar,cur + 'n',pos + 1,digits);
                    recurse(sofar,cur + 'o',pos + 1,digits);
                    break;
                case '7':
                    recurse(sofar,cur + 'p',pos + 1,digits);
                    recurse(sofar,cur + 'q',pos + 1,digits);
                    recurse(sofar,cur + 'r',pos + 1,digits);
                    recurse(sofar,cur + 's',pos + 1,digits);
                    break;
                case '8':
                    recurse(sofar,cur + 't',pos + 1,digits);
                    recurse(sofar,cur + 'u',pos + 1,digits);
                    recurse(sofar,cur + 'v',pos + 1,digits);
                    break;
                case '9':
                    recurse(sofar,cur + 'v',pos + 1,digits);
                    recurse(sofar,cur + 'w',pos + 1,digits);
                    recurse(sofar,cur + 'x',pos + 1,digits);
                    recurse(sofar,cur + 'y',pos + 1,digits);
                    recurse(sofar,cur + 'z',pos + 1,digits);
                    break;
            }
        }

    }
}
