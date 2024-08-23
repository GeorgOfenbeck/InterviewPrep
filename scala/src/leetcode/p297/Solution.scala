package leetcode.p297


class TreeNode(var _value: Int) {
  var value: Int = _value
  var left: TreeNode = null
  var right: TreeNode = null
}


class Codec {
    val spacer = 'X'
    val nullMarker = 'N'
    // Encodes a list of strings to a single string.
    def serialize(root: TreeNode): String = {
        val sb = new StringBuffer()
        rec(sb, root)
        sb.toString()
    }

    def rec(sb: StringBuffer, root: TreeNode): Unit = {
        if (root == null){
            sb.append(nullMarker)
        } else{
            sb.append(root.value)
            sb.append(spacer)
            rec(sb, root.left)
            sb.append(spacer)
            rec(sb, root.right)
            sb.append(spacer)
        }
    }
    
    // Decodes a single string to a list of strings.
    def deserialize(data: String): TreeNode = {
       val splits = data.split(spacer).toList
       val (tree, _) = recreate(splits) 
       tree
    }

    def recreate(remain: List[String]): (TreeNode, List[String]) = {
        if(remain.isEmpty){
            return (null, List.empty)
        } else {
            val head = remain.head
            if (head == s"${nullMarker}")
                return (null, remain.tail)
            else{
                val node = TreeNode(head.toInt) //error check
                val (left, leftremain) = recreate(remain.tail)
                val (right, rightremain) = recreate(leftremain)
                node.left = left
                node.right = right
                (node, rightremain)
            }
        }
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * var ser = new Codec()
 * var deser = new Codec()
 * val s = ser.serialize(root)
 * val ans = deser.deserialize(s)
 */
