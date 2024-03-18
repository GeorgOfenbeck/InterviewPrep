package scala


import java.util.Date
import java.util.Calendar
import java.text.SimpleDateFormat

object CalTest extends App{
  val start_date = "2019-10-01" // starting date for extracting ODL customers
  val days_len = 30 // how many days we need to process?
  val address_days_save = "/data/ivr/dev/bill-shock/odldaily/"

  val date1: Date = new SimpleDateFormat("yyyy-MM-dd").parse(start_date)
  val myformat: SimpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd")
  println(myformat.format(date1))
  val mycal: Calendar = Calendar.getInstance()
  mycal.setTime(date1)
  println(mycal)

  // saving customers id on daily basis into seperated dataframes
  for(i <- 0 to days_len){
    println(mycal.toString)
    mycal.add(Calendar.DATE, 1)
  }

}
