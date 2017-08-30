package tutorial

object TutorialData {
  /* Insert the key as received from the service registration email */
  val key = "B6560B8C2E9538D03ECFF87F588E2DDE9254631528B88E5BDE9E2B43D1868538"
}

object Utils {
  
  import java.io.PrintWriter
  import java.io.File
  
  /**
   * Writes some contents into a file.
   */
  def write(str: String, fileName: String = "output.xml") = {
     val writer = new PrintWriter(new File(fileName))
      writer.write(str)
      writer.close()
  }
  
}