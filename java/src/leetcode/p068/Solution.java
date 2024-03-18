package leetcode.p068;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String [] args){
        //String [] words = {"Science","is","what","we","understand","well","enough","to","explain",
         //       "to","a","computer.","Art","is","everything","else","we","do"};
        //Integer maxWidth = 20;

        //String [] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String [] words = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};

        Integer maxWidth = 16;
        Solution sol = new Solution();
        List<String> res = sol.fullJustify(words,maxWidth);
        for (String s : res){
            System.out.println(s);
        }

    }

    public List<String> fullJustify(String[] words, int maxWidth) {

        ArrayList<Integer> sizes = Arrays.asList(words)
                .stream()
                .map(p -> p.length())
                .collect(Collectors.toCollection(ArrayList::new));
        LinkedList<String> result = new LinkedList<>();
        int totalSize = 0;
        int startIndex = 0;
        for (int i = 0; i < sizes.size(); i ++){
            if (totalSize + sizes.get(i) <= maxWidth){ //we can add it
                totalSize = totalSize + sizes.get(i) + 1; //1 because we need at least a space after
            } else {
                //we are to big
                result.add(toSpacedString(words,startIndex,i,maxWidth,sizes));
                startIndex = i;
                totalSize = sizes.get(i) + 1; //1 because we need at least a space after
            }
        }
        //we are always not handling the last row
        result.add(toString(words,startIndex,words.length,maxWidth,sizes));
        return result;
    }

    public String toString(String[] words, int start, int end, int maxWidth, ArrayList<Integer> sizes){
        StringBuffer sb = new StringBuffer();
        for (int i = start; i< end-1; i++){
            sb.append(words[i]);
            sb.append(" ");
        }
        sb.append(words[end-1]);
        while(sb.length() < maxWidth)
            sb.append(' ');
        return sb.toString();
    }

    public String toSpacedString(String[] words, int start, int end, int maxWidth, ArrayList<Integer> sizes){
        if (end - start == 1) {
            StringBuffer sb = new StringBuffer();
            sb.append(words[start]);
            while(sb.length() < maxWidth)
                sb.append(' ');
            return sb.toString();
        }

        int nrWords = end - start;
        int totalLength = 0;
        for (int i = start; i < end; i++){
            totalLength += sizes.get(i);
        }
        int spacing = (maxWidth - totalLength)/(nrWords-1);
        int div = (maxWidth - totalLength) - (spacing * (nrWords-1));

        StringBuffer sb = new StringBuffer();


        for (int i = start; i < end-1; i++){
            sb.append(words[i]);
            for (int j = 0; j < spacing; j ++){
                sb.append(' ');
            }
            if (div > 0)
            {
                sb.append(' ');
                div --;

            }
        }
        sb.append(words[end-1]);

        return sb.toString();
    }
}
