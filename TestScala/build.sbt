name := "Test Scala"
version := "1.0"
scalaVersion := "2.10.5"

resolvers += "Secured Central Repository" at "https://repo1.maven.org/maven2"

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

mainClass in Compile := Some("com.example.TestSparkGraph")

libraryDependencies ++= Seq(
    "io.spray" %% "spray-json" % "1.3.2",
    "org.apache.spark" %% "spark-core" % "1.5.0",
    "org.apache.spark" %% "spark-graphx" % "1.5.0",
    "org.apache.spark" %% "spark-mllib" % "1.5.0"
    )

EclipseKeys.withSource := true

// mainClass in Compile := Some("com.example.TestSparkGradientBoostedTrees")
// mainClass in Compile := Some("com.example.TestSparkRandomForest")
mainClass in Compile := Some("com.example.TestSparkRDDSaveAndLoad")
