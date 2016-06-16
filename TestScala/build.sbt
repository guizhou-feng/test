name := "Test Scala"
version := "1.0"
scalaVersion := "2.10.5"

resolvers += "Secured Central Repository" at "https://repo1.maven.org/maven2"

externalResolvers := Resolver.withDefaultResolvers(resolvers.value, mavenCentral = false)

libraryDependencies ++= Seq(
    "io.spray" %% "spray-json" % "1.3.2",
    "org.apache.spark" %% "spark-core" % "1.5.0"
    )

EclipseKeys.withSource := true

