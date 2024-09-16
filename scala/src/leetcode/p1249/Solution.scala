package leetcode.p1249

object Solution {
  def minRemoveToMakeValid(s: String): String = {
    val sbf = StringBuffer(s.size)
    val sbb = StringBuffer(s.size)

    var open = 0
    for (c <- s) {
      c match {
        case '(' => {
          open = open + 1
          sbf.append(c)
        }
        case ')' => {
          if (open > 0) {
            `open` = `open` - 1
            sbf.append(c)
          }
        }
        case _ => {
          sbf.append(c)
        }
      }
    }

    val cleaned_forward = sbf.reverse().toString()

    open = 0
    for (c <- cleaned_forward) {
      c match {
        case ')' => {
          open = open + 1
          sbb.append(c)
        }
        case '(' => {
          if (open > 0) {
            `open` = `open` - 1
            sbb.append(c)
          }
        }
        case _ => {
          sbb.append(c)
        }
      }
    }
    sbb.reverse().toString()
  }
}
