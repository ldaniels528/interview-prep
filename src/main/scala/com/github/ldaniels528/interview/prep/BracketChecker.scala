package com.github.ldaniels528.interview.prep

/**
  * Implement a function that returns whether a string made of different bracket characters is well formed or not.
  *
  * For example,
  * "{({})[]}" is a well formed bracket string
  * "{[](}" is not a well formed bracket string
  *
  * Needless to say any single brackets are automatically counted as not well formed
  *
  * @see https://www.careercup.com/question?id=5762451793510400
  */
object BracketChecker extends App {

  val strings = Seq("{({})[]}" -> true, "{[](}" -> false)
  strings foreach { case (string, expected) =>
    println(s""" "$string" should be $expected """)
    assert(isValid(string) == expected)
  }

  def isValid(string: String): Boolean = {
    val brackets = Seq(
      ('{', '}'), ('[', ']'), ('(', ')')
    )

    brackets forall { case (startBracket, endBracket) =>
      isValid(string, startBracket, endBracket)
    }
  }

  def isValid(string: String, startBracket: Char, endBracket: Char): Boolean = {
    var level = 0
    string foreach { c =>
      if (c == startBracket) level += 1
      else if (c == endBracket) level -= 1

      // fail-safe
      if(level < 0) return false
    }
    level == 0
  }

}
