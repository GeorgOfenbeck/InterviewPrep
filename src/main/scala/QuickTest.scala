package scala
import java.time.{LocalDateTime, Month, ZoneId}


object QuickTest  extends App {



    def printit(metrics: Map[String, Long]): Unit = {
      for ((k, v) <- metrics)
        println(s"key: $k, value: $v")
    }

    val t = new HeartBeat()
    t.start()

    class HeartBeat extends Thread {
      override def run(): Unit = {
        while (true) {
          val heartbeatSignal = Map("heartbeat" -> System.currentTimeMillis())
          printit(heartbeatSignal)
          Thread.sleep(3000) // every five minutes
        }
      }
    }
}

