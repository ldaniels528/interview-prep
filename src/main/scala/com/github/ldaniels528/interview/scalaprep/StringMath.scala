package com.github.ldaniels528.interview.scalaprep

object StringMath extends App {

  val number0 = "1482"
  val number1 = "12"
  println(s"$number0 + $number1 = ${add(number0, number1)}")
  println(s"$number0 * $number1 = ${multiply(number0, number1)}")

  def multiply(numberL: String, numberR: String) = {
    val products = numberR.reverse.map(_ - '0') map { digitR =>
      var carry = 0

      // generate the product
      (numberL.reverse.map(_ - '0') map { digitL =>
        val product = digitL * digitR + carry
        val digit = product % 10
        carry = product / 10
        digit
      }).mkString.reverse
    }

    products match {
      case Seq(product) => product
      case list =>
        list.tail.zipWithIndex.foldLeft(list.head) {
          case (sum, (product, index)) => add(sum, product + ("0" * (1 + index)))
        }
    }
  }

  def add(numberL: String, numberR: String): String = {
    val limit = Math.max(numberL.length, numberR.length)
    val digitsL = numberL.map(_ - '0').reverse
    val digitsR = numberR.map(_ - '0').reverse
    var carry = 0
    (for {
      n <- 0 until limit
    } yield {
      val digitL = if (n < digitsL.length) digitsL(n) else 0
      val digitR = if (n < digitsR.length) digitsR(n) else 0
      val sum = digitL + digitR + carry
      val digit = sum % 10
      carry = sum / 10
      digit
    }).mkString.reverse
  }

}
