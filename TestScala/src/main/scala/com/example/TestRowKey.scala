package com.example

import java.util.Date
import java.text.SimpleDateFormat

object TestRowKey extends App {
  def getReversedTime(dateTime: Date): Long = {
    return Long.MaxValue - dateTime.getTime;
  }
  val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  
  def getTimeFromReversed(key: Long): String = {
    val time = new Date(Long.MaxValue - key)
    formatter.format(time)
  }
  
  println(getReversedTime(formatter.parse("2016-07-14 00:00:00")))
  println(getReversedTime(formatter.parse("2016-07-13 00:00:00")))
  println(getReversedTime(formatter.parse("2016-07-12 00:00:00")))
  println(getReversedTime(formatter.parse("2016-01-31 00:00:00")))
  println(getReversedTime(formatter.parse("2016-01-20 00:00:00")))
  println(getReversedTime(formatter.parse("2015-07-30 00:00:00")))
  println(getReversedTime(formatter.parse("2015-07-15 00:00:00")))
  println(getReversedTime(formatter.parse("2015-07-14 00:00:00")))
  
  val t1 = "2016-01-01 00:00:00"
  val t2 = "2016-04-01 00:00:00"
  val t3 = "2016-07-01 00:00:00"
  val t3_1 = "2016-07-16 00:00:00"
  val t4 = "2016-10-01 00:00:00"
  val t5 = "2017-01-01 00:00:00"
  println("2016-01-17 00:00:00 =" + getReversedTime(formatter.parse("2016-01-17 00:00:00")))
  println("2016-03-01 00:00:00 =" + getReversedTime(formatter.parse("2016-03-01 00:00:00")))
  println("2016-01-16 03:00:00 =" + getReversedTime(formatter.parse("2016-01-16 03:00:00")))
  println()
  println(s"$t1 =" + getReversedTime(formatter.parse(t1)))
  println(s"$t2 =" + getReversedTime(formatter.parse(t2)))
  println(s"$t3 =" + getReversedTime(formatter.parse(t3)))
  println(s"$t3_1 =" + getReversedTime(formatter.parse(t3_1)))
  println(s"$t4 =" + getReversedTime(formatter.parse(t4)))
  println(s"$t5 =" + getReversedTime(formatter.parse(t5)))
  
  println(getTimeFromReversed(9223370566793231251L))
  println(getTimeFromReversed(9223370566792271308L))
  println(getTimeFromReversed(9223370568451442930L))
  println(getTimeFromReversed(9223370568455592066L))
  println(getTimeFromReversed(9223370568450812533L))
  println(getTimeFromReversed(9223370566475198284L))
  println(getTimeFromReversed(9223370569638913807L))
  println(getTimeFromReversed(9223370574961471807L))
  println(getTimeFromReversed(9223370585256723807L))
  
  
  println("Parse a file")
  def parseFileForReversedTime() = {
    val filePath = "/Users/pfeng/Documents/bigdata/hbase/order_summary_index_2016-07-13_reversetime_result"
    val source = scala.io.Source.fromFile(filePath)
    try source.getLines().toSeq.sorted.foreach { l => println(l + " = " + getTimeFromReversed(l.toLong)) } 
    finally source.close()
  }
  // parseFileForReversedTime()
  
}