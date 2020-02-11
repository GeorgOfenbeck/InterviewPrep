package combinatorics.Nina;

public class DAGEdge {

    Vaccination from;
    Vaccination to;
    int months;

    DAGEdge(Vaccination from,Vaccination to, int months){
        this.from = from;
        this.to = to;
        this.months = months;
    }

}
