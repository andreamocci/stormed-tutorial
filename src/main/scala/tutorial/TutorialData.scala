package tutorial

object TutorialData {
  /* Insert the key as received from the service registration email */
  val key = ???
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