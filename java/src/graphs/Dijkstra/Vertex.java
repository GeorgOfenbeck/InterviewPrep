package graphs.Dijkstra;


//own file
class Vertex {

  Integer id;
  Edge[] edges;

  Vertex(Integer pid) {
    id = pid;
  }


  Vertex(Edge[] pedge) {
    edges = pedge;
  }
}
