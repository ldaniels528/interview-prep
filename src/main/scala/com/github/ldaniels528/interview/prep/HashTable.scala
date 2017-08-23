package com.github.ldaniels528.interview.prep

object HashTable extends App {

  val map = new HashType[String, String](10)
  map.put("1", "Hello")
  map.put("2", "World")
  map.put("3", "From")
  map.put("4", "Scala")

  println(s"map.get('1') => ${map.get("1")}")
  println(s"keys => ${map.keys}")
  println(s"values => ${map.values}")


  class HashType[K, V](slots: Int) {
    private val buckets: Array[List[HashEntry[K, V]]] = (0 until slots).map(_ => Nil).toArray

    def contains(key: K): Boolean = {
      val index = key.hashCode() % buckets.length
      val bucket = Option(buckets(index)) getOrElse Nil
      bucket.exists(_.key == key)
    }

    def get(key: K): Option[V] = {
      val index = key.hashCode() % buckets.length
      for {
        bucket <- Option(buckets(index))
        entry <- bucket.find(_.key == key)
      } yield entry.value
    }

    def keys: Set[K] = buckets.toList.flatMap(_.map(_.key)).toSet

    def put(key: K, value: V): Option[V] = {
      val index = key.hashCode() % buckets.length
      val bucket = Option(buckets(index)) getOrElse Nil
      val entry_? = bucket.find(_.key == key)
      val previous_? = entry_?.map(_.value)
      entry_? match {
        case None => buckets(index) = HashEntry(key, value) :: bucket
        case Some(entry) => entry.value = value
      }
      previous_?
    }

    def values: Set[V] = buckets.toList.flatMap(_.map(_.value)).toSet

  }

  case class HashEntry[K, V](key: K, var value: V)

}
