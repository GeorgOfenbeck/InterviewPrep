package leetcode.p2402

import scala.collection.{mutable => mu}

object Solution {
  def mostBooked(n: Int, meetings: Array[Array[Int]]): Int = {
    // meeting[i] =  [starti, endi]
    // half closed [inclusive, exclusive)
    //
    // starti is unique
    //
    // always room with lowest number
    //
    // if no room available, delayed till free room - duration stays same
    //
    //
    //
    //

    // min -heap for starti -> take from there
    // second min heap => time/nr
    // counter for each room

    val tupording = new Ordering[(Long, Int)] {
      override def compare(x: (Long, Int), y: (Long, Int)): Int = {
        val cmp = x._1.compareTo(y._1)
        if (cmp == 0) {
          x._2.compareTo(y._2)
        } else {
          cmp
        }
      }
    }
    val roomQueue = mu.PriorityQueue.empty[(Long, Int)](
      tupording.reverse
    ) // order by endi asc/ room asc

    val meetingOrdering =
      Ordering.by[(Long, Int), Long](_._1) // starti, meetingIndex
    val meetingQueue = mu.PriorityQueue.empty[(Long, Int)](
      meetingOrdering.reverse
    ) // order by starti asc
    val nrOfMeetingsPerRoom = mu.HashMap.empty[Int, Int]

    for (i <- 0 until meetings.size) {
      meetingQueue.enqueue((meetings(i)(0), i)) // by starti
    }

    for (i <- 0 until n) {
      roomQueue.enqueue((0, i)) // endi / roomNR
    }

    while (meetingQueue.nonEmpty) {
      // get the next free room slot
      val (freeRoomEndTime, freeRoomNr) = roomQueue.dequeue()
      val (starti, meetingIndex) = meetingQueue.dequeue()

      val meetingDuration =
        meetings(meetingIndex)(1) - meetings(meetingIndex)(0)
      if (starti <= freeRoomEndTime) {
        // we are already delayed - take first room

        val newEndTime = freeRoomEndTime + meetingDuration
        roomQueue.enqueue((newEndTime, freeRoomNr))
        nrOfMeetingsPerRoom.updateWith(freeRoomNr) {
          case Some(nr) => Some(nr + 1)
          case None     => Some(1)
        }

      } else {
        // more meeting Rooms might get free
        val roomOrder =
          Ordering.by[(Long, Int), Int](_._2) // starti, meetingIndex
        val minHeapRoomNr = mu.PriorityQueue.empty[(Long, Int)](
          roomOrder.reverse
        ) // order by roomID asc
        roomQueue.enqueue((freeRoomEndTime, freeRoomNr))
        while (roomQueue.nonEmpty && roomQueue.head._1 <= starti) {
          val (endTime, roomNr) = roomQueue.dequeue()
          minHeapRoomNr.enqueue((endTime, roomNr))
        }
        val (chosenEndTime, chosenRoomNr) = minHeapRoomNr.dequeue()
        val newEndTime = starti + meetingDuration

        roomQueue.enqueue((newEndTime, chosenRoomNr))
        nrOfMeetingsPerRoom.updateWith(chosenRoomNr) {
          case Some(nr) => Some(nr + 1)
          case None     => Some(1)
        }

        while (minHeapRoomNr.nonEmpty) {
          roomQueue.enqueue(minHeapRoomNr.dequeue())
        }
      }

    }

    val (maxID, nrMeetings) =
      nrOfMeetingsPerRoom.maxBy((roomID, nrOfMeetings) => nrOfMeetings)

    println(nrOfMeetingsPerRoom)
    maxID
  }

  @main
  def test(): Unit = {
    // meetings = [[0,10],[1,5],[2,7],[3,4]]
    println(
      mostBooked(2, Array(Array(0, 10), Array(1, 5), Array(2, 7), Array(3, 4)))
    )
  }
  @main
  def test2(): Unit = {
    // n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
    println(
      mostBooked(
        3,
        Array(Array(1, 20), Array(2, 10), Array(3, 5), Array(4, 9), Array(6, 8))
      )
    )
  }
}
