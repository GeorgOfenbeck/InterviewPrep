/*package scala.collection


object ColUtil extends App{

  def addifexits[K,V](m: Map[K,Vector[V]],k: K, v: V): Map[K,Vector[V]] = {
    def add(col: Vector[V], v: V): Vector[V] = v +: col
    def empty: Vector[V] = Vector.empty[V]
    addifexits(m, k, v, empty, add)
  }

  def addifexits[K,V](m: Map[K,Set[V]],k: K, v: V): Map[K,Set[V]] = {
    def add(col: Set[V], v: V): Set[V] = col + v
    def empty: Set[V] = Set.empty[V]
    addifexits(m, k, v, empty, add)
  }

  def addifexits[K,V,S[V]](m: Map[K,S[V]],k: K, v: V, empty: => S[V], op: (S[V],V) => S[V]): Map[K,S[V]] =
    m.+((k -> m.get(k).fold(empty)(sofar => op(v))))



  val t = Vector(1,2,3,4).withFilter(x => x > 3)
}
*/