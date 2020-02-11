package combinatorics.Nina;

import java.util.ArrayList;

public class Vaccination extends DAGNode {
    String name;
    public ArrayList<DAGEdge> requiredParents= new ArrayList<>();;
    public ArrayList<ArrayList<DAGEdge>> optionalParents= new ArrayList<>();;

    public ArrayList<DAGEdge> requiredChildren= new ArrayList<>();;
    public ArrayList<ArrayList<DAGEdge>> optionalChildren= new ArrayList<>();

    Vaccination(String name){
        this.name = name;

    }

}
