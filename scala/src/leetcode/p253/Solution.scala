package leetcode.p253


object Solution {

    sealed abstract class TimeSlot{
        val idx: Int
        val timeslot: Int
    }
    class BookingStart(val idx: Int, val timeslot: Int) extends TimeSlot
    class BookingStop(val idx: Int, val timeslot: Int) extends TimeSlot
    
    
    def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
       
       var bookingList = List.empty[TimeSlot]

       for (i <- 0 until intervals.size){
            bookingList = BookingStart(i, timeslot = intervals(i)(0)) +: bookingList
            bookingList = BookingStop(i, timeslot = intervals(i)(1)) +: bookingList
       }

       val sortedBookings = bookingList.sortWith( (a,b) => {
         (a,b) match {
            case Tuple2(x: BookingStart,y: BookingStart) => a.timeslot < b.timeslot 
            case Tuple2(x: BookingStop,y: BookingStop) => a.timeslot < b.timeslot
            case Tuple2(x: BookingStart,y: BookingStop) => a.timeslot < b.timeslot 
            case Tuple2(x: BookingStop,y: BookingStart) => a.timeslot <= b.timeslot
         }
       })

       val openRooms = scala.collection.mutable.Set.empty[Int]

       var maxsize = 0
       for (e <- sortedBookings){
            e match {
                case _: BookingStart => {
                    openRooms.add(e.idx)
                    if(openRooms.size > maxsize)
                        maxsize = openRooms.size 
                }
                case _: BookingStop =>{
                    openRooms.remove(e.idx)
                }
            }
       }
       maxsize
    }
}