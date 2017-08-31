package tutorial

import ch.usi.inf.reveal.parsing.stormed.service._
import ch.usi.inf.reveal.parsing.model.visitors.NodeAccumulatorVisitor
import ch.usi.inf.reveal.parsing.model.CommentNode
import ch.usi.inf.reveal.parsing.artifact._
import ch.usi.inf.reveal.parsing.model.visitors.ArtifactVisitor
import ch.usi.inf.reveal.parsing.model.visitors.IdentifierNodeVisitor
import ch.usi.inf.reveal.parsing.model.java.MethodDeclarationNode
import ch.usi.inf.reveal.parsing.model.HASTNode
import ch.usi.inf.reveal.parsing.model.java.ForLoopStatementNode
import ch.usi.inf.reveal.parsing.model.java.CatchClauseNode
import ch.usi.inf.reveal.parsing.model.java.CatchClauseNode
import ch.usi.inf.reveal.parsing.model.java.WhileStatementNode
import ch.usi.inf.reveal.parsing.model.java.JavaImplicits._
import ch.usi.inf.reveal.parsing.model.java.JavaASTNode
import ch.usi.inf.reveal.parsing.model.java.IdentifierNode

object Tutorial5 extends App {
  
  /* 
   * http://stackoverflow.com/questions/28126043/answer-button-help-in-java-calculator 
   */
  val questionId: Int = 28126043
  
  /* Retrieves the json file path corresponding 
   * to the model of the particular 
   * Stack Overflow discussion.
   */
  val jsonFilePath = s"./data/$questionId.json"

  //Deserializes the artifact 
  val artifact = ArtifactSerializer.deserializeFromFile(jsonFilePath)
  
  // Collects the sequence of HAST nodes for the artifact.
  val hast = artifact.units.map{_.astNode}

  /**
   * Retrieves all the identifiers present in the discussion.
   */
  def retrieveIdentifiers(): Set[IdentifierNode] = {
    val listVisitor = IdentifierNodeVisitor.set()
    val identifiers = listVisitor(Set(),hast)
    identifiers
  }
  
  val uniqueIdentifiers = retrieveIdentifiers()
  println(s"Unique Identifiers found: ${uniqueIdentifiers.size}")
  
  /**
   * A filter to exclude the children of method declarations.
   */
  def excludeMethodDeclarators(element: ArtifactVisitor.Visitable) = 
  element match {
    	case node:MethodDeclarationNode => false
    	case _ => true
  }

  def retrieveIdentifiersOutsideMethods() = {
    val identifiersOutsideMethodsVisitor = 
      IdentifierNodeVisitor.set(excludeMethodDeclarators)  
    val identifiersOutsideMethods = 
      identifiersOutsideMethodsVisitor(Set(),hast)  
    identifiersOutsideMethods 
  }
  
  val uniqueIdentifiersOutsideMethods = retrieveIdentifiersOutsideMethods()
  println(s"Unique Identifiers outside Methods found: ${uniqueIdentifiersOutsideMethods.size}")
  

}
