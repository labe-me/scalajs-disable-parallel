import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object MyBuild extends Build {
  lazy val commonSettings = Seq(
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-unchecked",
      "-language:reflectiveCalls",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen",
      "-Xfuture",
      "-Xlint"
    )
  )

  lazy val jsSettings = Seq(
  )

  lazy val jsOne = Project(id="jsOne", base=file("js/one")).
    settings(commonSettings: _*).
    settings(jsSettings: _*).
    enablePlugins(ScalaJSPlugin)

  lazy val jsTwo = Project(id="jsTwo", base=file("js/two")).
    settings(commonSettings: _*).
    settings(jsSettings: _*).
    enablePlugins(ScalaJSPlugin)

  lazy val js = Project(id = "js", base = file("js")).
    settings(commonSettings: _*).
    aggregate(jsShared, jsFinder, jsManager, jsCast, jsUser, jsUiTest)

  lazy val root: Project = Project(id = "root", base = file(".")).
    settings(commonSettings: _*).
    aggregate(js)
}
