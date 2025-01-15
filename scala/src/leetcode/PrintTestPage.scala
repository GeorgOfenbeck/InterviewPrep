package leetcode


import java.awt.print._
import javax.print._
import javax.print.attribute._
import javax.print.attribute.standard._
import java.awt.Graphics

object PrintTestPage {
  @main
  def main(): Unit = {
    // Create a simple Printable object
    val printable = new Printable {
      def print(g: Graphics, pageFormat: PageFormat, pageIndex: Int): Int = {
        if (pageIndex != 0) return Printable.NO_SUCH_PAGE
        g.drawString("Test Print", 100, 100)
        Printable.PAGE_EXISTS
      }
    }

    // Get the default printer service
    val printService = PrintServiceLookup.lookupDefaultPrintService()

    // Check if the printer is "Microsoft Print to PDF"
    if (printService != null && printService.getName == "Microsoft Print to PDF") {
      // Create a print job
      val job = printService.createPrintJob()

      // Set up print attributes
      val attributes = new HashPrintRequestAttributeSet()
      attributes.add(new JobName("Test Print Job", null))
      attributes.add(new Destination(new java.net.URI("file:///C:/Users/rayda/output.pdf")))

      // Print the document
      try {
        job.print(new SimpleDoc(printable, DocFlavor.SERVICE_FORMATTED.PRINTABLE, null), attributes)
        println("Print job sent successfully.")
      } catch {
        case e: Exception => e.printStackTrace()
      }
    } else {
      println("Microsoft Print to PDF printer not found.")
    }
  }
}
