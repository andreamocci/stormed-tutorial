package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._


object Tutorial1 extends App {
  
  // from http://stackoverflow.com/questions/886955/breaking-out-of-nested-loops-in-java
  val textToParse = """
    I've got a nested loop construct like this:

    for (Type type : types) {
        for (Type t : types2) {
             if (some_condition) {
                 // Do something and break...
                 break; // Breaks out of the inner loop
             }
        }
    }
    
    public static void main(String[] args)
    
    Now how can I break out of both loops. I've looked at similar questions, but none 
    concerns Java specifically. I couldn't apply these solutions because most used gotos.
    
    I don't want to put the inner loop in a different method.
    
    Update: I don't want to rerun the loops, when breaking I'm finished with the 
    execution of the loop block.
    
    --

    I never use labels. It seems like a bad practice to get into. Here's what I would do:

    boolean finished = false;
    
    and after this there is this complicated loop:
    
    for (int i = 0; i < 5 && !finished; i++) {
        ...
        for (int j = 0; j < 5; j++) {
            if (i * j > 6) {
                finished = true;
                break;
            }
        }
    }
    
    --
    
    (EDIT: Like other answerers, I'd definitely prefer to put the inner loop
    in a different method. This answer just shows how the requirements in 
    the question can be met.)

    You can use break with a label for the outer loop. For example:
    
    public class Test {
      public static void main(String[] args) {
        outerloop:
        for (int i=0; i < 5; i++) {
          for (int j=0; j < 5; j++) {
            if (i * j > 6) {
              System.out.println("Breaking");
              break outerloop;
            }
            System.out.println(i + " " + j);
          }
        }
        System.out.println("Done");
      }
    }

    """.trim
  
  val result = StormedService.tag(textToParse,false,TutorialData.key)
  
  result match {
    case TaggingResponse(result, quota, status) =>
      println(s"Status: $status")
      println(s"Quota Remaining: $quota")
      Utils.write(result)
      println("Parsing Result written.")
      
    case ErrorResponse(message, status) =>
      println(status + ": " + message)
  }
}
