package c4.t1;

import java.util.*;

public class Graph {

    Boolean adj_list[][];



    private int[] neighbours(int from){
        int size = 0;
        for (int i = 0; i < adj_list[from].length; i++)
            if (adj_list[from][i] == true)
                size++;
        int[] neigh = new int[size];
        int j = 0;
        for (int i = 0; i < adj_list[from].length; i++)
            if (adj_list[from][i] == true) {
                neigh[j] = i;
                j++;
            }
        return neigh;
    }

    class ReturnTuple{
        Set<Integer> visited;
        Queue<Integer> todo;
        Set<Integer> other;
        Boolean success;

        ReturnTuple(Set<Integer> ovisited, Queue<Integer> otodo, Set<Integer> oother, Boolean osucess){
            visited = ovisited;
            todo = otodo;
            other = oother;
            success = osucess;
        }
    }

    public ReturnTuple visit_neigh(Set<Integer> visited, Queue<Integer> todo, Set<Integer> other){
        if(todo.isEmpty())
            return new ReturnTuple(visited,todo, other,false);
        else
        {
            Queue<Integer> newtodo = new LinkedList<Integer>();
            for (int q = 0; q < todo.size(); q ++) {
                int[] neigh = this.neighbours(todo.remove());
                for (int i = 0; i < neigh.length; i++) {
                    if (visited.contains(neigh[i])) {

                    } else {
                        newtodo.add(neigh[i]);
                        visited.add(neigh[i]);
                        if (other.contains(neigh[i]))
                            return new ReturnTuple(visited, todo, other, true);
                    }
                }
            }
            return new ReturnTuple(visited,newtodo, other, false);
        }
    }

    public Boolean find_path(int start, int end){
        Set<Integer> visiteda = new HashSet<Integer>();
        visiteda.add(start);
        Set<Integer> visitedb = new HashSet<Integer>();
        visitedb.add(end);
        Queue<Integer> todoa = new LinkedList<Integer>();
        todoa.add(start);
        Queue<Integer> todob = new LinkedList<Integer>();
        todob.add(end);

        ReturnTuple a = new ReturnTuple(visiteda, todoa, visitedb,false);
        ReturnTuple b = new ReturnTuple(visitedb,todob,a.visited, false);

        while (!a.todo.isEmpty() || !b.todo.isEmpty()) {
            a = visit_neigh(a.visited, a.todo, b.visited);

            if (a.success) return true;
            else {
                b = visit_neigh(visitedb, todob, a.visited);
                if (b.success) return true;
            }
        }
        return false;
    }
}
