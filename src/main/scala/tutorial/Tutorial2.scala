package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._
import ch.usi.inf.reveal.parsing.model.Implicits._
import ch.usi.inf.reveal.parsing.model.TextFragmentNode

object Tutorial2 extends App {
  
  val textToParse = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit
    
    public static void main(int args[])
    
    Is this a valid declaration?
    
    fooMethod(int a)
    
    And this?
    
    new ClassConstructor(3 + x.y)
    
    incompleteness is everywhere on these artifacts:
    
    am.i.something.NoteWorthy ?
    
    Proin tincidunt tristique ante, sed lacinia leo fermentum quis.
    Fusce in magna eu ante tincidunt euismod nec eu ligula.
    Integer someInteger;
    
    This is sparta!
    for (int x = 0; x < 30; x ++) 
      for (int y = 0; y < 50; y ++) 
        return x+y;

    while (true)
      while (readChar() != '\n');
        for (int i = 0; i < list.len; i++) 
          if (list.get(i).equals(x))
            return true;
            
    """.trim
  
  val result = StormedService.parseOption(textToParse,TutorialData.key)
  Utils.write(StormedService.parseAsJson(textToParse, TutorialData.key), "output.json")
  
  result foreach { seq =>
      val allTextFragments = seq.filter { fragment => fragment.isInstanceOf[TextFragmentNode] }
      
      allTextFragments.foreach { node =>
        println(node.toCode)
        
        //println(node)
      }      
  }
}
