package com.github.ldaniels528.interview.scalaprep.util

object ComparableHelper {

  final implicit class ComparableExtensions[T <: Comparable[T]](val a: T) extends AnyVal {

    @inline def >(b: T): Boolean = a.compareTo(b) > 0

    @inline def >=(b: T): Boolean = a.compareTo(b) >= 0

    @inline def <(b: T): Boolean = a.compareTo(b) < 0

    @inline def <=(b: T): Boolean = a.compareTo(b) <= 0

  }

}
