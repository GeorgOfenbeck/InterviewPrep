
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


object LeetCodep1206 extends Properties("Skiplist"){
  property("") = forAll{ (l: List[Int]) =>
    val skiplist = new leetcode.p1206.Skiplist()
    l.map(x => skiplist.add(x))
    l.map(x => skiplist.erase(x))

    skiplist.layers.isEmpty

  }
}
