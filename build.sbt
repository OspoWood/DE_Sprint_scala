ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "DE_Sprint_scala"
  )

libraryDependencies += "com.lihaoyi" %% "requests" % "0.7.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0"