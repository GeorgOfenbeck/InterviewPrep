package leetcode.p374

class MaxHeap {
  // Define an implicit ordering for the priority queue to behave as a max heap based on the second Int in the tuple
  val maxHeapOrdering: Ordering[(Int, Int)] =
    Ordering.by[(Int, Int), Int](_._2)

  // Create a priority queue with the custom ordering
  val heap: scala.collection.mutable.PriorityQueue[(Int, Int)] =
    scala.collection.mutable.PriorityQueue.empty(maxHeapOrdering)

  // Method to insert an element into the heap
  def insert(element: (Int, Int)): Unit = {
    heap.enqueue(element)
  }

  // Method to extract the maximum element from the heap
  def extractMax(): Option[(Int, Int)] = {
    if (heap.nonEmpty) Some(heap.dequeue())
    else None
  }

  // Method to get the maximum element without removing it
  def getMax(): Option[(Int, Int)] = {
    heap.headOption
  }

  // Method to check if the heap is empty
  def isEmpty: Boolean = {
    heap.isEmpty
  }
  

}
object Solution {
  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val count = scala.collection.mutable.SortedMap.empty[Int, Int]

    for (n <- nums) {
      count.updateWith(n) {
        case None        => Some(1)
        case Some(value) => Some(value + 1)
      }
    }
    val maxheap = new MaxHeap
    for ((t) <- count) {
      maxheap.insert(t)
    }

    val result: Array[Int] = Array.ofDim(k)
    for (i <- 0 until k) {
      result(i) = maxheap.extractMax().get._1
    }
    result
  }


  @main
  def main()={
    val res = topKFrequent(Array(1),1)
    println(res)
  }
}
