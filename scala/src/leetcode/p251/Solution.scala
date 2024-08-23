package leetcode.p251

class Vector2D(_vec: Array[Array[Int]]) {
    var lists = _vec.toList.flatten
    
    

    def next(): Int = {
        val ele = lists.head
        lists = lists.tail
        ele
    }

    def hasNext(): Boolean = {
       !lists.isEmpty 
    }

}

/**
 * Your Vector2D object will be instantiated and called as such:
 * val obj = new Vector2D(vec)
 * val param_1 = obj.next()
 * val param_2 = obj.hasNext()
 */
