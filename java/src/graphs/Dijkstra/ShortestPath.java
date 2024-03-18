package graphs.Dijkstra;

import java.util.*;

public class ShortestPath {



  class VertexCost implements Comparable<VertexCost> {

    Vertex v;
    Integer cost;

    VertexCost(Vertex pv, Integer pcost) {
      v = pv;
      cost = pcost;
    }

    @Override
    public int compareTo(VertexCost other) {
      if (other.cost > cost) {
        return -1;
      } else {
        if (other.cost < cost) {
          return 1;
        } else {
          return 0;
        }
      }
    }
  }

  public static void main(String[] args) {

    Vertex a = new Vertex(0);
    Vertex b = new Vertex(1);
    Vertex c = new Vertex(2);
    Vertex d = new Vertex(3);
    Vertex e = new Vertex(4);

    Edge ad = new Edge(1,d);
    Edge da = new Edge(1,a);
    Edge ab = new Edge(6,b);
    Edge ba = new Edge(6,a);
    Edge db = new Edge(2,b);
    Edge bd = new Edge(2,d);
    Edge de = new Edge(1,e);
    Edge ed = new Edge(1,d);
    Edge be = new Edge(2,e);
    Edge eb = new Edge(2,b);
    Edge bc = new Edge(5,c);
    Edge cb = new Edge(5,b);
    Edge ec = new Edge(5,c);
    Edge ce = new Edge(5,e);

    a.edges = new Edge[]{ad,ab};
    b.edges = new Edge[]{ba,bc,bd,be};
    c.edges = new Edge[]{cb,ce};
    e.edges = new Edge[]{eb,ec,ed};
    d.edges = new Edge[]{da,db,de};
    Graph g = new Graph();

    g.all = new Vertex[]{a,b,c,d,e};
    ShortestPath dij = new ShortestPath();
    dij.shortestPath(g,a,c);
  }

  public void shortestPath(Graph g, Vertex start, Vertex stop) {
    HashSet<Vertex> visited = new HashSet<>();
    HashMap<Vertex, Integer> weights = new HashMap<>();
    HashMap<Integer, Integer> from = new HashMap<>();
    PriorityQueue<VertexCost> min = new PriorityQueue<>();
    Arrays.stream(g.all).forEach(p -> {
      weights.put(p, Integer.MAX_VALUE);
    });

    weights.put(start, 0); //faster then if in loop
    min.add(new VertexCost(start, 0));

    recurse(visited, weights, min, from);
    System.out.println("");

  }

  private void recurse(
      HashSet<Vertex> visited,
      HashMap<Vertex, Integer> weights,
      PriorityQueue<VertexCost> min,
      HashMap<Integer, Integer> from
  )

  {
    if (min.isEmpty()) return;
    VertexCost current = min.poll();
    if (visited.contains(current.v))
      recurse(visited, weights, min, from);
    else {
      //for each outgoing edge - update the weights of all connected verticies
      Arrays.stream(current.v.edges).forEach(p -> {
        if (!visited.contains(p.to)) {
          Vertex target = p.to;
          Integer curcost = weights.get(target);
          Integer pathcost = current.cost + p.weight;
          if (curcost > pathcost) {
            weights.put(target, pathcost);
            from.put(target.id, current.v.id);
          }
          min.add(new VertexCost(target, pathcost));
        }
      });
      visited.add(current.v);

      recurse(visited, weights, min, from);
    }
  }
}


