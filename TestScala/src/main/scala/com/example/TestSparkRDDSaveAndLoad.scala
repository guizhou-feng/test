package com.example

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.SparkConf
import org.apache.spark.graphx.GraphXUtils
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import scala.collection.JavaConverters._

/**
 * @author pfeng
 */
object TestSparkRDDSaveAndLoad extends App {

    val sc = new SparkContext()
    
    println("==============" + System.getProperty("abc"))
    println("==============" + System.getProperty("spark.driver.extraJavaOptions"))
    println("==============" + sc.getConf.get("spark.driver.extraJavaOptions", "empty"))
    println("==============" + sc.getConf.get("spark.app.name", "empty"))
    println("-------------------------Start")
    sc.getConf.getAll.foreach(f => println(f._1 + "=" + f._2))
    println("-------------------------End")
    
    val users: RDD[(VertexId, (String, String))] =
    sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
      (5L, ("franklin", "prof")), (2L, ("istoica", "prof")),
      (4L, ("peter", "student"))))
    val fs = FileSystem.get(sc.hadoopConfiguration);
    
    val fsConfigIterator = fs.getConf.iterator()
    println("-------------------------Start")
//    while (fsConfigIterator.hasNext()) {
//      val entry = fsConfigIterator.next();
//      println(entry.getKey + " : " + entry.getValue)
//    }
//    fsConfigIterator.asScala.foreach(f => println(f.getKey + " :: " + f.getValue))
    
    println("-------------------------End")
    val path = new Path("tmp.rdd")
    println("Folder exists: " + fs.exists(path))
    if (fs.exists(path)) {
      fs.delete(path, true)
    }
    users.saveAsObjectFile("tmp.rdd")
    val loadedRDD = sc.objectFile("tmp.rdd", 2)
    
    println("Loaded Records: " + loadedRDD.count())
    // println("Records: " + Seq(loadedRDD.take(3)).mkString(","))
      
}
