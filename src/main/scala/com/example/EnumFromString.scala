package com.example

import shapeless.labelled.{FieldType, field}
import shapeless._

/*
  Trying a tip via Chris Birchall:

  Taken from https://github.com/ovotech/comms-kafka-messages/blob/fd3466f1c2f4abc55992d5ed0a0062a814222c7b/src/main/scala/com/ovoenergy/comms/util/EnumFromString.scala
 */

trait EnumFromString[A] {
  def fromString(string: String): Option[A]
}

object EnumFromString {

  implicit val fromStringCNil: EnumFromString[CNil] = new EnumFromString[CNil] {
    override def fromString(string: String) = None
  }

  implicit def fromStringCCons[K <: Symbol, V, R <: Coproduct](
      implicit wit: Witness.Aux[K],
      gen: LabelledGeneric.Aux[V, HNil],
      right: EnumFromString[R]): EnumFromString[FieldType[K, V] :+: R] =
    new EnumFromString[FieldType[K, V] :+: R] {
      override def fromString(string: String) = {
        if (wit.value.name == string)
          Some(Inl(field[K](gen.from(HNil))))
        else
          right.fromString(string).map(Inr(_))
      }
    }

  implicit def enumFromString[A, Repr <: Coproduct](
      implicit gen: LabelledGeneric.Aux[A, Repr],
      reprFromString: EnumFromString[Repr]): EnumFromString[A] =
    new EnumFromString[A] {
      override def fromString(string: String) =
        reprFromString.fromString(string).map(gen.from)
    }

  def apply[A](implicit ev: EnumFromString[A]): EnumFromString[A] = ev

}
