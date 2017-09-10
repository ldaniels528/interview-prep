package com.github.ldaniels528.interview.scalaprep

/**
  * Given two integers, add them without using any arithmetic operators
  */
object NonArithmeticAddition extends App {

  val num0 = 5 // 0101
  val num1 = 6 // 0110

  // x = 0101
  // y = 0110

  // carry = (0101 & 0110) << 1 => 0100 << 1 => 1000
  // x = 0101 ^ 0110 => 0011
  // y = 1000

  // carry = (0011 & 1000) << 1 => 0000
  // x = 0011 ^ 1000 => 1011
  // y = 0000

  println(s"$num0 + $num1 = ${add(num0, num1)}")

  def add(n0: Int, n1: Int): Int = {
    var x = n0
    var y = n1
    while (y != 0) {
      val carry = (x & y) << 1
      x = x ^ y
      y = carry
    }
    x
  }

}
