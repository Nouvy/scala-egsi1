
import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "esgi1",
    libraryDependencies ++= Seq(
    "mysql" % "mysql-connector-java" % "8.0.33",
    )
  )
