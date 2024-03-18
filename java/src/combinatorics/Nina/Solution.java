package combinatorics.Nina;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Solution {


    public static void main(String[] args) throws IOException {
        Vaccination birth = new Vaccination("Birth");

        Vaccination ebola1 = new Vaccination("Ebola1");
        Vaccination ebola2 = new Vaccination("Ebola2");
        Vaccination ebola3 = new Vaccination("Ebola3");

        Vaccination malaria1 = new Vaccination("Malaria1");
        Vaccination malaria2 = new Vaccination("Malaria2");

        Vaccination aids1 = new Vaccination("Aids1");
        Vaccination aids2a = new Vaccination("Aids2a");
        Vaccination aids2b = new Vaccination("Aids2b");

        Vaccination malariaAids = new Vaccination("MalariaAids");

        Vaccination malaria3 = new Vaccination("Malaria3");


        DAGEdge birthEbola1 = new DAGEdge(birth, ebola1, 16);
        DAGEdge birthMalaria1 = new DAGEdge(birth, malaria1, 12);
        DAGEdge birthAids1 = new DAGEdge(birth, aids1, 10);

        birth.requiredChildren.add(birthEbola1);
        birth.requiredChildren.add(birthMalaria1);
        birth.requiredChildren.add(birthAids1);

        ebola1.requiredParents.add(birthEbola1);
        malaria1.requiredParents.add(birthMalaria1);
        aids1.requiredParents.add(birthAids1);

        DAGEdge ebola1Ebola2 = new DAGEdge(ebola1, ebola2, 3);
        ebola1.requiredChildren.add(ebola1Ebola2);
        ebola2.requiredParents.add(ebola1Ebola2);

        DAGEdge ebola2Ebola3 = new DAGEdge(ebola2, ebola3, 1);
        ebola2.requiredChildren.add(ebola2Ebola3);
        ebola3.requiredParents.add(ebola2Ebola3);

        DAGEdge malaria1Malaria2 = new DAGEdge(malaria1, malaria2, 2);
        DAGEdge malaria2Malaria3 = new DAGEdge(malaria2, malaria3, 3);

        DAGEdge malaria1MalariaAids = new DAGEdge(malaria1, malariaAids, 4);
        //DAGEdge malariaAidsMalaria3 = new DAGEdge(malariaAids,malaria2,6);

        DAGEdge aids1MalariaAids = new DAGEdge(aids1, malariaAids, 5);
        //DAGEdge malariaAids = new DAGEdge(aids1,malariaAids, 5);

        DAGEdge aids1aids2a = new DAGEdge(aids1, aids2a, 10);
        DAGEdge aids1aids2b = new DAGEdge(aids1, aids2b, 12);

        ArrayList<DAGEdge> malaria1option1 = new ArrayList<>();
        malaria1option1.add(malaria1Malaria2);

        malaria2.requiredParents.add(malaria1Malaria2);
        malaria1.optionalChildren.add(malaria1option1);

        ArrayList<DAGEdge> malaria1option2 = new ArrayList<>();
        malaria1option2.add(malaria1MalariaAids);

        malaria1.optionalChildren.add(malaria1option2);

        malaria2.requiredChildren.add(malaria2Malaria3);

        ArrayList<DAGEdge> malaria3option1 = new ArrayList<>();
        malaria3option1.add(malaria2Malaria3);
        malaria3.optionalParents.add(malaria3option1);


        malariaAids.requiredParents.add(malaria1MalariaAids);
        malariaAids.requiredParents.add(aids1MalariaAids);

        DAGEdge malariaaidsMalaria3 = new DAGEdge(malariaAids, malaria3, 6);

        ArrayList<DAGEdge> malaria3option2 = new ArrayList<>();
        malaria3option2.add(malariaaidsMalaria3);
        malaria3.optionalParents.add(malaria3option2);

        malariaAids.requiredChildren.add(malariaaidsMalaria3);

        ArrayList<DAGEdge> aids1option1 = new ArrayList<>();
        aids1option1.add(aids1MalariaAids);

        ArrayList<DAGEdge> aids1option2 = new ArrayList<>();
        aids1option2.add(aids1aids2a);
        aids1option2.add(aids1aids2b);

        aids1.optionalChildren.add(aids1option1);
        aids1.optionalChildren.add(aids1option2);

        aids2a.requiredParents.add(aids1aids2a);
        aids2b.requiredParents.add(aids1aids2b);

        Vaccination[] all = {
                birth, ebola1, ebola2, ebola3, malaria1, malaria2, malaria3, malariaAids, aids1, aids2a, aids2a
        };

        printGraph(all,true,true, "full.graph");
        printGraph(all,true,false, "children.graph");
        printGraph(all,false,true, "parents.graph");

        System.out.println(numbers(birth));

    }

    public static int numbers(Vaccination root) throws IOException {

        int and = 1;

        for (DAGEdge p : root.requiredChildren)
        {
            and = and * numbers(p.to);
        }

        int or = 0;
        for (ArrayList<DAGEdge> ar : root.optionalChildren)
        {
            int inner = 1;
            for (DAGEdge p : ar)
            {
                inner = inner * numbers(p.to);
            }
            or = or + inner;
        }

        if (or > 0) {
            or = or - root.optionalChildren.size() + 1;
            return 1 + and * or;

        }
        else
            return 1 + and;
    }

    public static void printGraph(Vaccination[] all, boolean children, boolean parents, String name) throws IOException {
        String[] colors = {"red", "green", "blue"};
        PrintWriter printWriter = new PrintWriter(new FileWriter(name));
        printWriter.println("digraph{");

        for (Vaccination x : all) {
            if (parents)
                for (DAGEdge e : x.requiredParents) {
                    printWriter.println(e.to.name + " -> " + e.from.name + "[style=dashed, label=\"" + e.months + "\"]");
                }
            if (children)
                for (DAGEdge e : x.requiredChildren) {
                    printWriter.println(e.from.name + " -> " + e.to.name + "[label=\"" + e.months + "\"]");
                }
            int color = 0;
            if (parents)
                for (ArrayList<DAGEdge> option : x.optionalParents) {

                    for (DAGEdge e : option) {
                        printWriter.println(e.to.name + " -> " + e.from.name + "[color=" + colors[color] + ",style=dashed, label=\"" + e.months + "\"]");
                    }
                    color++;
                }

            color = 0;
            if (children)
                for (ArrayList<DAGEdge> option : x.optionalChildren) {

                    for (DAGEdge e : option) {
                        printWriter.println(e.from.name + " -> " + e.to.name + "[color=" + colors[color] + ", label=\"" + e.months + "\"]");
                    }
                    color++;
                }

        }


        printWriter.println("}");

        printWriter.close();
    }


}
