package leetcode.p017

object Solution {
    def letterCombinations(digits: String): List[String] = {
        
        if (digits == "") 
            return List.empty[String]

        val mapping: Map[Char, List[Char]] = Map(
            ('2' -> List('a','b', 'c')),
            ('3' -> List('d','e', 'f')),
            ('4' -> List('g','h', 'i')),
            ('5' -> List('j','k', 'l')),
            ('7' -> List('p','q', 'r', 's')),
            ('8' -> List('t','u', 'v')),
            ('9' -> List('w','x', 'y', 'z')),
            ('6' -> List('m','n', 'o')),
        )

        var remaindigits = digits.toList
        rec(digits.toList, mapping, "" , List.empty[String])    
    }

    def rec( remaindigits: List[Char], mapping: Map[Char, List[Char]], sofar: String, res: List[String]): List[String] = {
       if (remaindigits.isEmpty){
        return sofar +: res
       } 
       val curdigit = remaindigits.head
       var letters = mapping(curdigit)
       var newres = res 
       while (!letters.isEmpty){
            val curletter = letters.head 
            newres = rec(remaindigits.tail, mapping, s"${sofar}${curletter}", newres ) 
            letters = letters.tail
       }
       newres
    }
}
