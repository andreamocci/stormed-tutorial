package ch.usi.inf.reveal.parsing.stormed.service

import ch.usi.inf.reveal.parsing.model.HASTNode

trait Request
case class ParsingRequest(text: String, key: String) extends Request
case class TaggingRequest(text: String, tagged: Boolean, key: String) extends Request

trait Response {
  val status: String
}
case class ParsingResponse(result: Seq[HASTNode], quotaRemaining: Int, status: String) extends Response
case class TaggingResponse(result: String, quotaRemaining: Int, status: String) extends Response
case class ErrorResponse(message: String, status: String) extends Response




