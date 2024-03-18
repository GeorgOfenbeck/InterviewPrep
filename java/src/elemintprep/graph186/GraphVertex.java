package elemintprep.graph186;


import java.util.*;

public class GraphVertex {

  public int d = -1;

  public List<GraphVertex> edges = new ArrayList<>();

  public static boolean isAnyPlacementFeasible(List<GraphVertex> G){
    for (GraphVertex v : G){
      if (v.d == -1 && !BFS(v)){ //univisited vertex
        return false;
      }
    }
    return true;
  }

  private static boolean BFS(GraphVertex s){
    s.d = 0;
    Queue<GraphVertex> q = new ArrayDeque<>();
    q.add(s);

    while (!q.isEmpty()){
      for (GraphVertex t : q.peek().edges){
        if (t.d == -1){
          t.d = q.peek().d + 1;
          q.add(t);
        } else
          if (t.d == q.peek().d){
          return false;
          }
      }
      q.remove();
    }
    return true;
  }

}
