package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._
import scala.collection.mutable.LinkedHashSet

object Tutorial1 extends App {
  
  val textToParse = """Some text, and after some class:
    東京はとてもすごいです。
    public class Foo<T> { 
    String[] foo; 
    public Integer bar() {}; 
    class Bar {} 
    // in a class
    class Alice<T,U> {
      String mirror = null;  
    }
    Foo<Bar> fooBar;
    int[] intArray;
    }""".trim
  
  val result = StormedService.parse(textToParse,TutorialData.key)
  
  result match {
    case ParsingResponse(result, quota, status) =>
      println(s"Status: $status")
      println(s"Quota Remaining: $quota")
      result.foreach { element => println(element.getClass) }
      println("Parsing Result written.")
      
    case ErrorResponse(message, status) =>
      println(status + ": " + message)
  }
}



