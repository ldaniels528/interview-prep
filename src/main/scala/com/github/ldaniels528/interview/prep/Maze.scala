package com.github.ldaniels528.interview.prep

import scala.language.postfixOps

/**
  * Find the shortest distance through the maze
  */
object Maze extends App {
  private val maze = Seq(
    Seq(1, 0, 0, 0, 1, 1, 1, 0, 0, 1),
    Seq(1, 1, 1, 0, 1, 1, 0, 1, 0, 1),
    Seq(1, 0, 0, 0, 0, 1, 0, 1, 0, 0),
    Seq(1, 0, 1, 1, 0, 1, 0, 1, 1, 0),
    Seq(1, 0, 0, 0, 0, 1, 1, 0, 0, 0),
    Seq(1, 0, 1, 1, 1, 1, 0, 0, 1, 1),
    Seq(1, 0, 0, 1, 0, 0, 0, 0, 0, 1),
    Seq(1, 1, 0, 1, 0, 1, 1, 0, 0, 1),
    Seq(1, 0, 0, 0, 0, 1, 0, 1, 0, 1),
    Seq(1, 1, 0, 1, 1, 1, 0, 1, 0, 1)).toArray

  private val rows = maze.length
  private val columns = maze.head.length
  private val empty = 0
  private val wall = 1

  // create the initial actors
  var actors = findStartingColumns.map(column => Actor(column = column)).toList

  show()

  // move each actor around the maze
  do {
    actors foreach { actor =>
      actor.getAvailableMoves match {
        case Nil =>
        case moves =>
          moves.tail foreach { direction =>
            actors = {
              val newActor = actor.copy()
              newActor.move(direction)
              newActor
            } :: actors
          }
          actor.move(moves.head)
      }
    }
  } while (!actors.forall(_.isFinished))

  // print out the actors
  val winners = actors.filter(_.isWinner).sortBy(_.moves)
  winners.headOption foreach { actor =>
    println(s"Winner: $actor")
  }

  def findStartingColumns: Seq[Int] = maze.head.zipWithIndex filter { case (cell, _) => cell == empty } map (_._2)

  def show(): Unit = {
    for {
      row <- maze
    } {
      println(row map {
        case 0 => "."
        case 1 => "+"
        case _ => "?"
      } mkString " ")
    }
  }

  case class Actor(var column: Int, var row: Int = 0, var heading: Option[Direction] = None, var moves: Int = 0) {

    def canMoveForward: Boolean = row + 1 < rows && maze(row + 1)(column) == empty

    def canMoveLeft: Boolean = column > 0 && maze(row)(column - 1) == empty

    def canMoveRight: Boolean = column + 1 < columns && maze(row)(column + 1) == empty

    def getAvailableMoves: List[Direction] = {
      var list: List[Direction] = Nil
      if (canMoveForward) list = Forward :: list
      if (canMoveLeft && !heading.contains(Right)) list = Left :: list
      if (canMoveRight && !heading.contains(Left)) list = Right :: list
      list
    }

    def isBlocked: Boolean = !isWinner && getAvailableMoves.isEmpty

    def isWinner: Boolean = row == rows - 1

    def isFinished: Boolean = isWinner || isBlocked

    def move(direction: Direction): Unit = {
      moves += 1
      heading = Some(direction)
      direction match {
        case Forward => row += 1
        case Left => column -= 1
        case Right => column += 1
      }
    }

    override def toString = s"${getClass.getSimpleName}(column:$column, row:$row, heading:${heading.orNull}, moves:$moves)"

  }

  sealed trait Direction

  case object Forward extends Direction

  case object Left extends Direction

  case object Right extends Direction

}
