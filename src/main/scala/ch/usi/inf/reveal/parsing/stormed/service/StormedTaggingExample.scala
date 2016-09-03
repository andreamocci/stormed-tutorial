package ch.usi.inf.reveal.parsing.stormed.service

object StormedTaggingExample extends App {
  
  val textToTag = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit
    public static void main(int args[])
    Proin tincidunt tristique ante, sed lacinia leo fermentum quis.
    Fusce in magna eu ante tincidunt euismod nec eu ligula.
    List<Integer> someList;
    """.trim
    
    
  val inputWithMissingTagging = """
    <p>I have an abstract entity:</p>
    <pre><code>public abstract class Entity extends JPanel implements FocusListener</code></pre>
    <p>And I have a TextEntity:</p>
    <pre><code>public class TextEntity extends Entity</code></pre>
    <p>Inside TextEntity's constructor I want to put a JTextArea that will cover the panel:</p>
    <pre>
      <code>
      textArea = new JTextArea();
      textArea.setSize(getWidth(),getHeight());
      add(textArea);
      </code>
    </pre>
    """.trim  

  val key = "<your API key>"  
  
  val taggedText = StormedService.tag(textToTag, false, key)
  val completeTagging = StormedService.tag(inputWithMissingTagging, true, key)
  
  println("###############")
  println("Example I")
  println("Plain text without tagging:")
  println("###############")
  println(textToTag)
  println("--------RESPONSE--------")
  printResult(taggedText)
  
  println()
  println("###############")
  println("Example II")
  println("Plain text without tagging:")
  println("###############")
  println(inputWithMissingTagging)
  println("--------RESPONSE--------")
  printResult(completeTagging)
  
  def printResult(result: Response) = result match {
    case TaggingResponse(result, quota, status) =>
      println(s"Status: $status")
      println(s"Quota Remaining: $quota")
      println(s"Tagged Text: $result")
    case ErrorResponse(message, status) =>
      println(status + ": " + message)
  }
}