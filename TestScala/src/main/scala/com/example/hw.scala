package com.example

import spray.json._
import DefaultJsonProtocol._

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
        
    }

}

case class abc(a: Integer, b: Integer)
