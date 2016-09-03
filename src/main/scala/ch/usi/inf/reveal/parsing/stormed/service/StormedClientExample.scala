package ch.usi.inf.reveal.parsing.stormed.service


object StormedClientExample extends App {
 		
  val codeToParse = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit
    public static void main(int args[])
    Proin tincidunt tristique ante, sed lacinia leo fermentum quis.
    Fusce in magna eu ante tincidunt euismod nec eu ligula.
    List<Integer> someList;
//    This is sparta!
//    for (int x = 0; x < 30; x ++) 
//      for (int x = 0; x < 30; x ++) 
//        return 3;
//    while (true)
//      while (false)
//        for (int y = 0; y < 0; y --);
    """.trim

  val key = "B6560B8C2E9538D03ECFF87F588E2DDE9254631528B88E5BDE9E2B43D1868538"  
  
  val result = StormedService.parse(codeToParse,key)
  
  result match {
    case ParsingResponse(result, quota, status) =>
      println(s"Status: $status")
      println(s"Quota Remaining: $quota")
      val nodeTypes = result.map{_.getClass.getSimpleName}
      println("Parsing Result: ")
      nodeTypes.foreach{println}
    case ErrorResponse(message, status) =>
      println(status + ": " + message)
  }
}
