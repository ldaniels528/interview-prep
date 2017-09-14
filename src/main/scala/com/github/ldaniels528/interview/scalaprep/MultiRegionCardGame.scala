package com.github.ldaniels528.interview.scalaprep

import com.github.ldaniels528.interview.scalaprep.MultiRegionCardGame.Suits.Suit

import scala.collection.concurrent.TrieMap
import scala.util.Random

/**
  * Design a geographically partitioned multi-player card game, that supports multiple players, multiple games at a time. 
  * Each game will have one contractor like ones we have in a bar, He can play a game or just watch it. 
  * Integrate payment systems. First HLD was required, use cases, flow diagram. then a low level design was required 
  * all necessary classes where will you use polymorphism, where inheritance, multithreading, synchronised approach 
  * if needed, socket connections
  */
object MultiRegionCardGame extends App {
  val regions = TrieMap[String, Region]()

  Deck().shuffle.cards foreach (card => println(s"card => $card"))

  case class Game(name: String) {
    val players: TrieMap[String, Player] = TrieMap()

  }


  case class Region(name: String) {
    val games: TrieMap[String, Game] = TrieMap()

  }

  case class Player(name: String) {
    var hand: List[Card] = Nil

  }

  case class Deck(cards: Seq[Card]) {
    var position = 0

    def take(): Card = {
      val card = cards(position % cards.length)
      position += 1
      card
    }

    def shuffle: Deck = Deck(cards = Random.shuffle(cards))

  }

  object Deck {

    def apply(): Deck = {
      Deck(cards =
        (for {number <- 1 to 9; suit <- Suits.values} yield NonFaceCard(value = number, suit = suit)).toList :::
          Suits.values.map(Ace.apply).toList :::
          Suits.values.map(Jack.apply).toList :::
          Suits.values.map(King.apply).toList :::
          Suits.values.map(Queen.apply).toList)
    }
  }

  sealed trait Card {

    def suit: Suit

    override def toString = s"${getClass.getSimpleName} of $suit"

  }

  trait FaceCard extends Card

  case class NonFaceCard(value: Int, suit: Suit) extends Card {

    override def toString = s"$value of $suit"
  }

  case class Ace(suit: Suit) extends FaceCard

  case class Jack(suit: Suit) extends FaceCard

  case class King(suit: Suit) extends FaceCard

  case class Queen(suit: Suit) extends FaceCard

  object Suits extends Enumeration {
    type Suit = Value

    val Hearts, Clubs, Spades, Diamonds = Value
  }

}
