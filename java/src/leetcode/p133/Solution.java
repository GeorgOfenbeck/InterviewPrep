package leetcode.p133;


import java.util.Iterator;
import java.util.*;


 class UndirectedGraphNode { int label;
 List<UndirectedGraphNode> neighbors;
 UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 };


class Solution {

    public static void main(String[] args){

    }
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Set<UndirectedGraphNode> seen = new HashSet<>();
        LinkedList<UndirectedGraphNode> todo = new LinkedList<>();
        UndirectedGraphNode cur;
        HashMap<Integer, ArrayList<Integer>> links = new HashMap<>();
        HashMap<Integer, UndirectedGraphNode> clones = new HashMap<>();
        todo.add(node);

        //loop to explore the graph
        while (!todo.isEmpty()){

            cur = todo.removeFirst();
            seen.add(cur);
            List<UndirectedGraphNode> neigh = null;
            if (cur.neighbors != null)
                neigh = cur.neighbors;
            else
                neigh = new ArrayList<>();
            ArrayList<Integer> neighlabel = new ArrayList<>(cur.neighbors.size());
            clones.put(cur.label,new UndirectedGraphNode(cur.label));
            neigh.forEach(name -> {
                if(!seen.contains(name)) todo.add(name);
                neighlabel.add(name.label);
            });
            links.put(cur.label,neighlabel);
        }
        //set the edges
        links.forEach( (key, value) -> {
            UndirectedGraphNode dolly = clones.get(key);
            value.forEach(n -> {
                dolly.neighbors.add(clones.get(n));
            });
        });
        return clones.get(node.label);
    }
}