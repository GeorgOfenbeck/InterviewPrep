name := "AlgorithmWorkBench"

version := "1.0"

organization := "ETHZ"

//scalaVersion := "0.2.0-RC1"
scalaVersion := "2.12.8"

scalaSource in Compile <<= baseDirectory(_ / "src/main")

scalaSource in Test <<= baseDirectory(_ / "src/test")

libraryDependencies += ("org.scala-lang" % "scala-compiler" % "2.11.11") //.withDottyCompat()

libraryDependencies += "org.scala-lang" % "scala-library" % scalaVersion.value

// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "27.0-jre"
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % "test"



// tests are not thread safe
parallelExecution in Test := false

// disable publishing of main docs
publishArtifact in (Compile, packageDoc) := false
