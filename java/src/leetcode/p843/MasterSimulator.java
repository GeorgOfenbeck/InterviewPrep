package leetcode.p843;

import java.util.HashMap;

class MasterSimulator implements Master{

    int [][] matrix;
    HashMap<String, Integer> hmap;
    String realword;

    MasterSimulator(int [][] matrix, String[] wordlist, String realword){
        this.matrix = matrix;
        hmap = new HashMap<>();
        for (int i = 0; i < wordlist.length; i++){
            hmap.put(wordlist[i],i);
        }
        this.realword = realword;
    }

    @Override
    public int guess(String word) {
        if (hmap.containsKey(word))
        {
            int i = hmap.get(word);
            int j = hmap.get(realword);
            return matrix[i][j];

        }
        else
            return -1;
    }
}
