package com.example

import io.circe.syntax._
import io.circe.generic.auto._

object Main {
  def main(args: Array[String]): Unit = {
    val person: Person = Person("hello", Role.User)

    val asJson = person.asJson
    println(asJson)
    val fromJson = asJson.as[Person]
    println(fromJson)

  }
}