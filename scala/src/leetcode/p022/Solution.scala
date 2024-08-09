package leetcode.p022


object Solution {
    def generateParenthesis(n: Int): List[String] = {
        if (n == 0) return List.empty
        else rec(List.empty, List.empty, 0, n)
    }


    def rec(ressofar: List[String], sofar: List[Char], nrOpenBrackets: Int, remainOpenBrackets: Int): List[String] = {
        val collect = ressofar
        
        if (remainOpenBrackets == 0){
            var l = sofar
            for (i <- 0 until nrOpenBrackets){
               l = ')' +: l 
            }            
            val rev = l.reverse
            val sb = StringBuffer(rev.length)
            for (c <- rev){
                sb.append(c)
            }
            return sb.toString() +: collect 
        } else {
            val openBracket = rec(collect, '(' +: sofar, nrOpenBrackets + 1, remainOpenBrackets - 1)
            val fin =  if (nrOpenBrackets > 0) {
                rec(openBracket, ')' +: sofar,nrOpenBrackets - 1, remainOpenBrackets)
            } else { 
                openBracket
            }
            return fin
        }
    }
}