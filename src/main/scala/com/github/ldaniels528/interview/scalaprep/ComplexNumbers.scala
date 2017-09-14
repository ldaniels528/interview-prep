package com.github.ldaniels528.interview.scalaprep

import scala.language.{implicitConversions, postfixOps}

/**
  * Complex Numbers
  */
object ComplexNumbers extends App {

  val x = 3 i
  val y = 5 i
  val z = x + y - 9
  println(s"x = $x, y = $y, z = $z")

  /**
    * Represents a Complex Numbers (e.g. 3i + 9)
    * @param value    the given complex value (e.g. 3i)
    * @param constant the given constant value (e.g. 9)
    */
  case class Complex(value: Double = 0, constant: Double = 0) {

    def *(number: Double): Complex = this.copy(value = value * number, constant = constant * number)

    def /(number: Double): Complex = this.copy(value = value / number, constant = constant / number)

    def +(number: Complex): Complex = this.copy(value = value + number.value, constant = constant + number.constant)

    def -(number: Complex): Complex = this.copy(value = value - number.value, constant = constant - number.constant)

    override def toString: String = {
      val sb = new StringBuilder(s"${value}i")
      if (constant > 0) sb.append(s" + $constant")
      else if (constant < 0) sb.append(s" - ${-constant}")
      sb.toString()
    }
  }

  object Complex {

    def sqrt(number: Double): Either[Complex, Double] = {
      number match {
        case n if n < 0 => Left(Complex(value = Math.sqrt(-n)))
        case n => Right(Math.sqrt(n))
      }
    }
  }

  implicit def byte2Complex(number: Byte): Complex = Complex(constant = number)

  implicit def double2Complex(number: Double): Complex = Complex(constant = number)

  implicit def float2Complex(number: Float): Complex = Complex(constant = number)

  implicit def int2Complex(number: Int): Complex = Complex(constant = number)

  implicit def long2Complex(number: Long): Complex = Complex(constant = number)

  implicit def number2Complex(number: Number): Complex = Complex(constant = number.doubleValue)

  implicit def short2Complex(number: Short): Complex = Complex(constant = number)

  final implicit class ComplexExtension(val number: Double) extends AnyVal {

    @inline
    def i: Complex = Complex(number)
  }

}
