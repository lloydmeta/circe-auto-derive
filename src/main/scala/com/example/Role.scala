package com.example

import io.circe.{Decoder, Encoder}

sealed trait Role
case object Role {
  case object Service extends Role
  case object User    extends Role

  implicit val encoder: Encoder[Role] = Encoder.encodeString.contramap(_.toString)

  implicit val decoder: Decoder[Role] = Decoder.decodeString.map {
    case "Service" => Service
    case "User"    => User
  }
}
