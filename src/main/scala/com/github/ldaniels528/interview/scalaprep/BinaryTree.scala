package com.github.ldaniels528.interview.scalaprep

import scala.annotation.tailrec
import scala.util.Random

object BinaryTree extends App {

  val random = new Random()
  val tree = new BSTree[Integer]()
  for(_ <- 1 to 10) tree.add(random.nextInt(100))
  tree.ascending() foreach(v => println(v))
  println("*" * 20)
  tree.descending() foreach(v => println(v))
  println("*" * 20)
  println(s"max: ${tree.max()}")
  println(s"min: ${tree.min()}")


  class BSTree[T <: Comparable[T]]() {
    private var root: BSTNode[T] = _

    def add(value: T): Unit = {
      if (root == null) root = BSTNode(value) else append(value, root)
    }

    def ascending(node: BSTNode[T] = root): List[T] = {
      if (node == null) Nil else ascending(node.left) ::: node.value :: ascending(node.right)
    }

    def descending(node: BSTNode[T] = root): List[T] = {
      if (node == null) Nil else descending(node.right) ::: node.value :: descending(node.left)
    }

    def max(node: BSTNode[T] = root): Option[T] = {
      if (node == null) None else max(node.right) ?? Option(node.value) ?? max(node.left)
    }

    def min(node: BSTNode[T] = root): Option[T] = {
      if (node == null) None else min(node.left) ?? Option(node.value) ?? min(node.right)
    }

    @tailrec
    private def append(value: T, node: BSTNode[T]): Unit = {
      value.compareTo(node.value) match {
        case r if r < 0 => if (node.left != null) append(value, node.left) else node.left = BSTNode(value)
        case r if r > 0 => if (node.right != null) append(value, node.right) else node.right = BSTNode(value)
        case _ =>
      }
    }

  }

  case class BSTNode[T](value: T, var left: BSTNode[T] = null, var right: BSTNode[T] = null)

  /**
    * Syntactic sugar for options
    * @param opt the given [[Option option]]
    */
  final implicit class OptionalMagic[A](val opt: Option[A]) extends AnyVal {

    @inline
    def ??(optB: Option[A]): Option[A] = if(opt.isEmpty) optB else opt

  }

}
