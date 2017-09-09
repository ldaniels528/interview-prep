package com.github.ldaniels528.interview.scalaprep

/**
  * Write a program that keeps the last 5 largest numbers from a stream of numbers
  */
object LastN extends App {

  val numbers = Seq(23, 5, 11, 83, 17, 31, 19, 43, 51, 7, 111, 61)

  val buffer = new Array[Int](5)
  for (n <- numbers) check(n)

  buffer foreach println

  def check(number: Int): Unit = {
    buffer.indexWhere(number >= _) match {
      case -1 =>
      case index =>
        System.arraycopy(buffer, index, buffer, index + 1, buffer.length - index - 1)
        buffer(index) = number
    }
  }


}
