package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._
import ch.usi.inf.reveal.parsing.model.Implicits._

object Tutorial3 extends App {
  
  val textToParse = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit MyClassName
    
    public static void main(int args[]) {
      return;
    }
        
    Proin tincidunt tristique ante, sed lacinia leo fermentum quis.
    Fusce in magna eu ante myMethodName tincidunt euismod nec eu ligula.
    
    public class MyClassName {

      You shouldn't use public modifiable collections:
      
      public List<Foo> field = null;
            
      This method is probably crashes:

      void bar() { field.remove(0); }

    }
    
  Can I parse this stuff?
  
  {
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 25,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021-3100"
  },
  "phoneNumbers": [
    {
      "type": "home",
      "number": "212 555-1234"
    },
    {
      "type": "office",
      "number": "646 555-4567"
    }
  ],
  "children": [],
  "spouse": null
  }
  
  Probably not:
  
  Exception in thread "main" java.lang.NullPointerException
        at com.example.myproject.Book.getTitle(Book.java:16)
        at com.example.myproject.Author.getBookTitles(Author.java:25)
        at com.example.myproject.Bootstrap.main(Bootstrap.java:14)
    
    """.trim
  
  val resultOption = StormedService.parseOption(textToParse,TutorialData.key)
  
  resultOption foreach { nodes =>      
      nodes.foreach { node =>
        println(node.getClass.getSimpleName)
      }
  }
}
