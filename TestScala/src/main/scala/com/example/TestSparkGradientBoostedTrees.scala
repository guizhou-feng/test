package com.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.util.MLUtils

/**
 * @author pfeng
 */
object TestSparkGradientBoostedTrees extends App {
  val libSVMFilePath = "/Users/pfeng/Materials/spark/ml/libsvm"
  val libSVMTrainFileName = "a1a"
  val libSVMTestFileName = "a1a.t"
  val conf = new SparkConf()
  .setMaster("local[2]")
  .setAppName("Test Spark MLLIB")
  val sc = new SparkContext(conf.setAppName("Test Spark MLLIB"))
  // Load and parse the data file.
  val data = MLUtils.loadLibSVMFile(sc, libSVMFilePath + "/" +libSVMTrainFileName)
  val testData = MLUtils.loadLibSVMFile(sc, libSVMFilePath + "/" + libSVMTestFileName)
  // Split the data into training and test sets (30% held out for testing)
  // val splits = data.randomSplit(Array(0.7, 0.3))
  // val (trainingData, testData) = (splits(0), splits(1))
  
  // Train a GradientBoostedTrees model.
  // The defaultParams for Classification use LogLoss by default.
  val boostingStrategy = BoostingStrategy.defaultParams("Classification")
  boostingStrategy.numIterations = 3 // Note: Use more iterations in practice.
  boostingStrategy.treeStrategy.numClasses = 2
  boostingStrategy.treeStrategy.maxDepth = 5
  // Empty categoricalFeaturesInfo indicates all features are continuous.
  boostingStrategy.treeStrategy.categoricalFeaturesInfo = Map[Int, Int]()
  
  val model = GradientBoostedTrees.train(data, boostingStrategy)
  
  // Evaluate model on test instances and compute test error
  val labelAndPreds = testData.map { point =>
    val prediction = model.predict(point.features)
    (point.label, prediction)
  }
  val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
  println("Test Error = " + testErr)
  println("Learned classification GBT model:\n" + model.toDebugString)
  
  // Save and load model
  model.save(sc, "target/tmp/myGradientBoostingClassificationModel")
  val sameModel = GradientBoostedTreesModel.load(sc,
    "target/tmp/myGradientBoostingClassificationModel")
}