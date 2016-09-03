package ch.usi.inf.reveal.parsing.stormed.service

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.read
import org.json4s.native.Serialization.write
import ch.usi.inf.reveal.parsing.artifact.ArtifactSerializer.formats

object ResponseSerializer extends CustomSerializer[Response](format => (
  {
    case obj: JObject =>
      val name = (obj \ "status").extract[String]
      name match {
        case "OK" => obj.extract[ParsingResponse]
        case "ERROR" => obj.extract[ErrorResponse]
      }
  },
  {
    case x: Response => write(x)
  }
))
