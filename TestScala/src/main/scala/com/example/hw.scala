package com.example

import spray.json._
import DefaultJsonProtocol._
import java.util.Date
import java.text.SimpleDateFormat

object Hi {
    implicit def abc(string: String) = new PimpString(string)
    
    def main(args: Array[String]) = {
        println("Hi!")
        println(List(1, 2, 3).toJson)
        "abc".printlnS
        
        val v = new abc(1, 2)
        println(v.productElement(0))
        println(v.productArity)
        println(v.productPrefix)
        println(v)
        
        val currentDate = new Date()
        println(currentDate.getTime)
        val newDate = new Date()
        newDate.setTime(currentDate.getTime)
        println(newDate.getTime)
        
        val defaultDateFormat = new SimpleDateFormat()
        defaultDateFormat.applyPattern("yyyy/MM/dd HH:mm:ss")
        println(defaultDateFormat.format(newDate))
        
        new B().hello()
        
        println(ModelingQueryResultColumnInfo.values().map { _.toString()}.contains("ORDERID"))
        
    }

}

class A {
  def hello() = {
    println("Hello " + txt)
    println(this.getClass.getName)
  }
  
  def txt = {
    "A"
  }
}

class B extends A {
  
  override def txt = {
    "B"
  }
}

case class abc(a: Integer, b: Integer)
