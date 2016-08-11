package com.example

object TestNullInScala extends App {
  val v = null
  val v1 = Some(null)
  val map = Map(
      "key1" -> {if (v == null) { null } else { 1 }},
      "key2" -> 1
      )
      
  println (map.mkString(","))
  
  val filteredMap = map.filter(_._2 != null)
  println (filteredMap.mkString(","))
  
  def coalesce[T](values: Option[T]*): Option[T] = 
    (List[T]() /: values)((prev: List[T], cur: Option[T]) =>
                          prev:::cur.toList).headOption

  println(coalesce(None,Some(3),Some(4)))
}