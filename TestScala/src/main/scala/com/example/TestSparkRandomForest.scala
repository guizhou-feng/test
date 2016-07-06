package com.example

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.tree.RandomForest
import org.apache.spark.mllib.regression.LabeledPoint

/**
 * @author pfeng
 */
object TestSparkRandomForest extends App {
  val libSVMFilePath = "/Users/pfeng/Materials/spark/ml/libsvm"
  val libSVMTrainFileName = "a1a"
  val libSVMTestFileName = "a1a.t"
  val conf = new SparkConf()
  .setMaster("local[2]")
  .setAppName("Test Spark MLLIB")
  val sc = new SparkContext(conf.setAppName("Test Spark MLLIB"))
  // Load and parse the data file.
  val data = MLUtils.loadLibSVMFile(sc, libSVMFilePath + "/" +libSVMTrainFileName).map {
    _ match {
      case LabeledPoint(l, f) if l < 0 => LabeledPoint(0.0, f)
      case LabeledPoint(l, f) => LabeledPoint(l, f)
    }
  }
  val testData = MLUtils.loadLibSVMFile(sc, libSVMFilePath + "/" + libSVMTestFileName).map {
    _ match {
      case LabeledPoint(l, f) if l < 0 => LabeledPoint(0.0, f)
      case LabeledPoint(l, f) => LabeledPoint(l, f)
    }
  }
  // Split the data into training and test sets (30% held out for testing)
  // val splits = data.randomSplit(Array(0.7, 0.3))
  // val (trainingData, testData) = (splits(0), splits(1))
  
  // Train a RandomForest model.
  // Empty categoricalFeaturesInfo indicates all features are continuous.
  val numClasses = 2
  val categoricalFeaturesInfo = Map[Int, Int]()
  val numTrees = 3 // Use more in practice.
  val featureSubsetStrategy = "auto" // Let the algorithm choose.
  val impurity = "gini"
  val maxDepth = 4
  val maxBins = 32
  val model = RandomForest.trainClassifier(data, numClasses, categoricalFeaturesInfo,
  numTrees, featureSubsetStrategy, impurity, maxDepth, maxBins)

  // Evaluate model on test instances and compute test error
  val labelAndPreds = testData.map { point =>
    val prediction = model.predict(point.features)
    (point.label, prediction)
  }
  val testErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / testData.count()
  println("Test Error = " + testErr)
  println("Learned classification forest model:\n" + model.toDebugString)
  
  // Save and load model
  model.save(sc, "target/tmp/myRandomForestClassificationModel")
}