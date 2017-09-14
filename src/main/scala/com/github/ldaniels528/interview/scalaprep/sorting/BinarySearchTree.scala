package com.github.ldaniels528.interview.scalaprep.sorting

import scala.annotation.tailrec
import scala.util.Random

/**
  * Binary Search Tree
  */
object BinarySearchTree extends App {

  val random = new Random()
  val tree = new BSTree[Integer]()
  for (_ <- 1 to 10) tree.add(random.nextInt(100))
  tree.ascending foreach (v => println(v))
  println("*" * 20)
  tree.descending foreach (v => println(v))
  println("*" * 20)
  println(s"max: ${tree.max}")
  println(s"min: ${tree.min}")
  println(s"3rd largest: ${tree.nthLargest(3)}")
  println(s"3rd smallest: ${tree.nthSmallest(3)}")
  println(s"tree.nthLargest(5).exists(tree.contains)? ${tree.nthLargest(5).exists(tree.contains)}")
  println(s"tree.contains(-1)? ${tree.contains(-1)}")


  class BSTree[T <: Comparable[T]]() {
    private var root: BSTNode[T] = _

    def add(value: T): Unit = {
      if (root == null) root = BSTNode(value) else attach(value, root)
    }

    def ascending: Stream[T] = ascending(root)

    def contains(value: T): Boolean = {
      var node = root
      while (node != null) {
        value.compareTo(node.value) match {
          case r if r < 0 => node = node.left
          case r if r > 0 => node = node.right
          case _ => return true
        }
      }
      false
    }

    def descending: Stream[T] = descending(root)

    def max: Option[T] = max(root)

    def min: Option[T] = min(root)

    def nthLargest(nth: Int): Option[T] = {
      val list = descending.take(nth)
      if (list.size < nth) None else list.lastOption
    }

    def nthSmallest(nth: Int): Option[T] = {
      val list = ascending.take(nth)
      if (list.size < nth) None else list.lastOption
    }

    @tailrec
    private def attach(value: T, node: BSTNode[T]): Unit = {
      value.compareTo(node.value) match {
        case r if r < 0 => if (node.left != null) attach(value, node.left) else node.left = BSTNode(value)
        case r if r > 0 => if (node.right != null) attach(value, node.right) else node.right = BSTNode(value)
        case _ =>
      }
    }

    private def ascending(node: BSTNode[T]): Stream[T] = {
      if (node == null) Stream.empty else ascending(node.left) #::: node.value #:: ascending(node.right)
    }

    private def descending(node: BSTNode[T]): Stream[T] = {
      if (node == null) Stream.empty else descending(node.right) #::: node.value #:: descending(node.left)
    }

    private def max(node: BSTNode[T]): Option[T] = {
      if (node == null) None else max(node.right) ?? Option(node.value) ?? max(node.left)
    }

    private def min(node: BSTNode[T]): Option[T] = {
      if (node == null) None else min(node.left) ?? Option(node.value) ?? min(node.right)
    }

  }

  case class BSTNode[T](value: T, var left: BSTNode[T] = null, var right: BSTNode[T] = null)

  /**
    * Syntactic sugar for options
    * @param opt the given [[Option option]]
    */
  final implicit class OptionalMagic[A](val opt: Option[A]) extends AnyVal {

    @inline
    def ??(optB: Option[A]): Option[A] = if (opt.isEmpty) optB else opt

  }

}
