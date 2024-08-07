package leetcode.p116

class Node(var _value: Int) {
  var value: Int = _value
  var left: Node = null
  var right: Node = null
  var next: Node = null
}

object Solution {
  def connect(root: Node): Node = {
    if (root == null)
      return null
    //rec2(root, null)
    norec(root)
    root
  }

  def norec(root: Node): Unit = {

    var leftmost = root
    var pptr = leftmost

    //as long as there is a child layer
    while (pptr.left != null){ 
        //left child
        pptr.left.next  = pptr.right
        if (pptr.next == null)
            pptr.right.next = null
        else
            pptr.right.next = pptr.next.left
        
        pptr = pptr.next
        if (pptr == null){
            pptr = leftmost
            leftmost = pptr.left
        }

    }
  }

  def rec2(node: Node, next: Node): Unit = {
    node.next = next
    if (node.left != null)
      rec2(node.left, node.right)
    if (node.right != null) {
      if (node.next != null)
        rec2(node.right, node.next.left)
      else
        rec2(node.right, null)
    }
  }

  def rec(inlevel: List[Node]): Unit = {
    if (inlevel.isEmpty)
      return
    else {
      var nextlevel = List.empty[Node]
      var level = inlevel

      while (!level.isEmpty) {
        var ptr = level.head
        // build nextlevel - head of list is leftmost
        if (ptr.left != null)
          nextlevel = ptr.left +: nextlevel
        if (ptr.right != null)
          nextlevel = ptr.right +: nextlevel
        if (level.tail.isEmpty) {
          ptr.next = null
        } else {
          ptr.next = level.tail.head
        }
        level = level.tail
      }
      /* for( n <- nextlevel)
      {
        print(s"${n._value} -> ")
      }
      println
       */
      rec(nextlevel.reverse)
    }
  }

  @main
  def main() = {
    val one = Node(1)
    val two = Node(2)
    val three = Node(3)

    val four = Node(4)
    val five = Node(5)
    val six = Node(6)
    val seven = Node(7)
    one.left = two
    one.right = three

    two.left = four
    two.right = five

    three.left = six
    three.right = seven

    connect(one)
  }
}
