package leetcode.p843;


import java.util.*;

class Solution {
    /*
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] wordlistx = {"gaxckt","trlccr","jxwhkz","ycbfps","peayuf","yiejjw","ldzccp","nqsjoa","qrjasy","pcldos","acrtag","buyeia","ubmtpj","drtclz","zqderp","snywek","caoztp","ibpghw","evtkhl","bhpfla","ymqhxk","qkvipb","tvmued","rvbass","axeasm","qolsjg","roswcb","vdjgxx","bugbyv","zipjpc","tamszl","osdifo","dvxlxm","iwmyfb","wmnwhe","hslnop","nkrfwn","puvgve","rqsqpq","jwoswl","tittgf","evqsqe","aishiv","pmwovj","sorbte","hbaczn","coifed","hrctvp","vkytbw","dizcxz","arabol","uywurk","ppywdo","resfls","tmoliy","etriev","oanvlx","wcsnzy","loufkw","onnwcy","novblw","mtxgwe","rgrdbt","ckolob","kxnflb","phonmg","egcdab","cykndr","lkzobv","ifwmwp","jqmbib","mypnvf","lnrgnj","clijwa","kiioqr","syzebr","rqsmhg","sczjmz","hsdjfp","mjcgvm","ajotcx","olgnfv","mjyjxj","wzgbmg","lpcnbj","yjjlwn","blrogv","bdplzs","oxblph","twejel","rupapy","euwrrz","apiqzu","ydcroj","ldvzgq","zailgu","xgqpsr","wxdyho","alrplq","brklfk"};
        sol.findSecretWord(wordlistx, null);
    }*/


    class DecisionTree {
        //Set<Integer> choices; // these are the options we have
        HashMap<Integer, HashMap<Integer,DecisionTree>> children; //choice -> outcome -> Tree
        Integer lowestTreeChoice;
        DecisionTree(HashSet<Integer> ini){
            children = new HashMap<>();
            for (Integer i : ini){
                children.putIfAbsent(i,new HashMap<>());
            }
        }
    }


    public int pairWiseSimilarity(String a, String b) {
        if (a.equals(b)) return a.length();
        int sum = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) sum++;
        }
        return sum;
    }


    public void findSecretWord(String[] wordlist, Master master) {
        if (wordlist == null || wordlist.length == 0) return;
        int[][] matrix = new int[wordlist.length][wordlist.length];
        for (int i = 0; i < wordlist.length; i++)
            matrix[i] = new int[wordlist.length];

        for (int i = 0; i < wordlist.length; i++)
            for (int j = 0; j < wordlist.length; j++) {
                matrix[i][j] = pairWiseSimilarity(wordlist[i], wordlist[j]);
            }
        /*
        for (int i = 0; i < wordlist.length; i++) {
            for (int j = 0; j < wordlist.length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }*/

        //root.choices = new HashSet<>();
        HashSet<Integer> iniset = new HashSet<>();
        for (int i = 0; i < wordlist.length; i++)
            iniset.add(i);
        DecisionTree root = new DecisionTree(iniset);

        fillTree(root,matrix);
        optimalGuess(root,wordlist,master,9);

        //oneLevelTree(root,matrix);
        //greedyGuess(root,wordlist,master,10, matrix);
        //MasterSimulator mymaster = new MasterSimulator(matrix,wordlist,wordlist[1]);
        //greedyGuess(root,wordlist,mymaster,9, matrix);
        //System.out.println("blub");
    }


    void greedyGuess(DecisionTree tree, String[] wordlist, Master master, Integer max, int[][] matrix){
        if (max == 0) return;
        Integer outcome = master.guess(wordlist[tree.lowestTreeChoice]);
        if (outcome == wordlist[tree.lowestTreeChoice].length()) {
        //    System.out.println("found it " + max);
            return;
        }
        else {
            DecisionTree subtree = tree.children.get(tree.lowestTreeChoice).get(outcome);
            oneLevelTree(subtree,matrix);
            greedyGuess(subtree,wordlist,master,max - 1, matrix);
        }
    }

    void optimalGuess(DecisionTree tree, String[] wordlist, Master master, Integer max){
        if (max == 0) return;
        Integer outcome = master.guess(wordlist[tree.lowestTreeChoice]);
        if (outcome == wordlist[tree.lowestTreeChoice].length())
            return;
        else {
            DecisionTree subtree = tree.children.get(tree.lowestTreeChoice).get(outcome);
            optimalGuess(subtree,wordlist,master,max - 1);
        }
    }

    int oneLevelTree(DecisionTree node, int[][] matrix) {
        Set<Integer> toIterate = node.children.keySet();
        int smallestMax = Integer.MAX_VALUE;
        int smallestMaxChoice = -1;

        for (Integer o : toIterate) { // for each choice we could take
            //for each option
            int[] outcomes = matrix[o]; //what are the outcomes given that choice

            HashMap<Integer, HashSet<Integer>> outcome2choices = new HashMap<>();

            for (int i = 0; i < outcomes.length; i++) {
                int outcome = outcomes[i];
                if (toIterate.contains(i)) {
                    HashSet<Integer> sofar = outcome2choices.getOrDefault(outcome, new HashSet<>());
                    sofar.add(i);
                    outcome2choices.put(outcome, sofar);
                }
            }
            //after this we have e.g. outcome: 1 -> words: 1,3,5
            //HashMap<Integer, HashMap<Integer,DecisionTree>> children; //choice -> outcome -> Tree

            HashMap<Integer,DecisionTree> outcome2Tree = new HashMap<>();
            int biggestEntry = 0;
            for (Map.Entry<Integer,HashSet<Integer>> entry : outcome2choices.entrySet()){
                DecisionTree child = new DecisionTree(entry.getValue());
                outcome2Tree.put(entry.getKey(),child);
                if (entry.getValue().size() > biggestEntry)
                    biggestEntry = Math.max(entry.getValue().size(), biggestEntry);
            }
            if (biggestEntry < smallestMax){
                smallestMaxChoice = o;
                smallestMax = biggestEntry;
            }
            node.children.put(o,outcome2Tree);
        }
        node.lowestTreeChoice = smallestMaxChoice;
        return smallestMaxChoice;
    }


    int fillTree(DecisionTree node, int[][] matrix) {
        Set<Integer> toIterate = node.children.keySet();

        int lowestDepth = Integer.MAX_VALUE;
        int lowestDepthChoice = -1;

        for (Integer o : toIterate) { // for each choice we could take
            //for each option
            int[] outcomes = matrix[o]; //what are the outcomes given that choice

            HashMap<Integer, HashSet<Integer>> outcome2choices = new HashMap<>();

            for (int i = 0; i < outcomes.length; i++) {
                int outcome = outcomes[i];
                if (toIterate.contains(i)) {
                    HashSet<Integer> sofar = outcome2choices.getOrDefault(outcome, new HashSet<>());
                    sofar.add(i);
                    outcome2choices.put(outcome, sofar);
                }
            }
            //after this we have e.g. outcome: 1 -> words: 1,3,5
            //HashMap<Integer, HashMap<Integer,DecisionTree>> children; //choice -> outcome -> Tree

            HashMap<Integer,DecisionTree> outcome2Tree = new HashMap<>();
            int maxdepth = 1;
            for (Map.Entry<Integer,HashSet<Integer>> entry : outcome2choices.entrySet()){
                DecisionTree child = new DecisionTree(entry.getValue());
                if(child.children.keySet().size() > 1) {
                    int depth = fillTree(child, matrix); //recurse
                    maxdepth = Math.max(maxdepth,depth);
                }
                outcome2Tree.put(entry.getKey(),child);
            }
            if (maxdepth < lowestDepth){
                lowestDepthChoice = o;
                lowestDepth = maxdepth;
            }
            node.children.put(o,outcome2Tree);
        }
        node.lowestTreeChoice = lowestDepthChoice;
        return lowestDepthChoice;
    }

}
