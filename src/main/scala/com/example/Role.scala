package com.example

import io.circe.{Decoder, Encoder}

import scala.util.{Failure, Success}

sealed trait Role
case object Role {
  case object Service extends Role
  case object User    extends Role

  def fromString(s: String): Option[Role] = EnumFromString[Role].fromString(s)

  implicit val encoder: Encoder[Role] = Encoder.encodeString.contramap(_.toString)

  implicit val decoder: Decoder[Role] = Decoder.decodeString.emapTry { s =>
    fromString(s) match {
      case Some(rS) => Success(rS)
      case _        => Failure(new IllegalArgumentException)
    }
  }
}
