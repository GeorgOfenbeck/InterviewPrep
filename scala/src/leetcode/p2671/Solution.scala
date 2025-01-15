package leetcode.p2671

object blub {
  @main
  def main() = {
    val tracker = new FrequencyTracker()
    tracker.add(3)
    tracker.deleteOne(3)
    tracker.add(3)
    tracker.add(3)
    tracker.deleteOne(3)
    tracker.deleteOne(3)
    val an = tracker.hasFrequency(2)
    println(an)
  }
}
/*
Implement the FrequencyTracker class.

FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
void add(int number): Adds number to the data structure.
void deleteOne(int number): Deletes one occurrence of number from the data structure. The data structure may not contain number, and in this case nothing is deleted.
bool hasFrequency(int frequency): Returns true if there is a number in the data structure that occurs frequency number of times, otherwise, it returns false.
 */

class FrequencyTracker() {
  val num2freq = scala.collection.mutable.HashMap.empty[Int, Int]
  val freq2num = scala.collection.mutable.HashMap.empty[Int, Set[Int]]

  def modFrequency(frequency: Int, number: Int, change: Int): Unit = {
    freq2num.updateWith(frequency)(setoption =>
      setoption match {
        case None => None
        case Some(oldset) => {
          val newset = oldset - number
          if (newset.isEmpty)
            None
          else
            Some(newset)
        }
      }
    )
    val newfrequency = frequency + change
    if (newfrequency > 0)
      freq2num.updateWith(newfrequency)(setoption =>
        setoption match {
          case None         => Some(Set(number))
          case Some(oldset) => Some(oldset + (number))
        }
      )
  }

  def add(number: Int): Unit = {
    num2freq.updateWith(number)(valueoption =>
      valueoption match {
        case None => {
          freq2num.updateWith(1)(setoption =>
            setoption match {
              case None        => Some(Set(number))
              case Some(value) => Some(value + number)
            }
          )
          Some(1)
        }
        case Some(value) => {
          modFrequency(value, number, 1)
          Some(value + 1)
        }
      }
    )

  }

  def deleteOne(number: Int): Unit = {
    num2freq.updateWith(number)(valueoption =>
      valueoption match {
        case None => None
        case Some(value) => {
          modFrequency(value, number, -1)
          if (value - 1 == 0)
            None
          else
            Some(value - 1)
        }
      }
    )
  }

  def hasFrequency(frequency: Int): Boolean = {
    freq2num.get(frequency) match {
      case None        => false
      case Some(value) => if (value.isEmpty) false else true
    }
  }
}
