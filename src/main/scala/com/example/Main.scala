package com.example

import io.circe.syntax._
import io.circe.generic.auto._

object Main {
  def main(args: Array[String]): Unit = {

    val person = Person("hello", Role.User)

    // val _      = Person("hello", Role.Service) <- uncommenting this out makes things work

    val asJson = person.asJson
    println(asJson)
    val fromJson = asJson.as[Person]
    println(fromJson)

  }
}
