package leetcode.p227

class Relation {
  def knows(a: Int, b: Int): Boolean = true
}

class Solution extends Relation {
  def findCelebrity(n: Int): Int = {
    val seed = whoDoYouKnow(n, 0)

    var shared: Set[Int] = seed
    for (i <- 1 until n) {
      shared = whoDoYouKnowOfThose(shared, i)
    }

    if (shared.isEmpty || shared.size > 1)
      return -1
    else {
        val onlyself: Set[Int] = whoDoYouKnow(n, shared.head)
        if (onlyself.size == 1)
            return shared.head
        else 
            return -1
    }
  }

  def whoDoYouKnow(total: Int, you: Int): Set[Int] = {
    var peopleKnown = Set.empty[Int]
    for (i <- 0 until total) {
      if (you == i || knows(you, i)) {
        peopleKnown = peopleKnown + i
      }
    }
    peopleKnown
  }

  def whoDoYouKnowOfThose(those: Set[Int], you: Int): Set[Int] = {
    var peopleShared = Set.empty[Int]
    for (person <- those) {
      if (knows(you, person)) {
        peopleShared = peopleShared + person
      }
    }
    peopleShared
  }

}
