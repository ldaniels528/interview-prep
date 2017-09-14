package com.github.ldaniels528.interview.scalaprep

import com.github.ldaniels528.interview.scalaprep.util.ComparableHelper._

import scala.reflect.ClassTag

/**
  * Write a program that keeps the last 5 largest numbers from a stream of numbers
  */
object LastN extends App {
  val numbers = Seq(23, 5, 11, 83, 17, 31, 19, 43, 51, 7, 111, 61).map(x => x: Integer)

  val lastN = new LastN[Integer](5)
  for (n <- numbers) lastN.update(n)
  lastN.get foreach println

}

class LastN[T <: Comparable[T]](capacity: Int)(implicit ct: ClassTag[T]) {
  val buffer = new Array[T](capacity)

  def get: Seq[T] = buffer.toSeq

  def update(number: T): Int = {
    val index = buffer.indexWhere(number >= _)
    if (index != -1) {
      System.arraycopy(buffer, index, buffer, index + 1, buffer.length - index - 1)
      buffer(index) = number
    }
    index
  }

}