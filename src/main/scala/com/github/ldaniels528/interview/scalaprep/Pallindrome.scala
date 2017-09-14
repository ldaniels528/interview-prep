package com.github.ldaniels528.interview.scalaprep

object Pallindrome extends App {

  val value = "rotor"
  println(s"is '$value' a pallindrome? ${isPallindrome(value)}")

  def isPallindrome(s: String): Boolean = {
    val length = s.length
    val half = length / 2 + length % 2
    s.take(half) == s.takeRight(half).reverse
  }

}
