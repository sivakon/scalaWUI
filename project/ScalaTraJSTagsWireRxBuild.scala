import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._
import fr.iscpif.jsmanager.JSManagerPlugin._

object ScalaTraJSTagsWireRxBuild extends Build {
  val Organization = "fr.iscpif"
  val Name = "ScalaTraJSTagsWireRx"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "2.3.0"

  lazy val shared = project.in(file("./shared"))
                           .settings(scalaVersion := ScalaVersion)

  lazy val ui = Project(
    "ui",
    file("./ui"),
    settings = Defaults.defaultSettings ++ jsManagerSettings ++ Seq(
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"),
        "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"),
      libraryDependencies ++= Seq(
        "com.lihaoyi" %%% "autowire" % "0.1.2",
        "com.scalatags" %%% "scalatags" % "0.3.8",
        "com.scalarx" %%% "scalarx" % "0.2.5",
        "fr.iscpif" %%% "scaladget" % "0.1.0-SNAPSHOT",
        "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
        "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6",
      "org.scala-lang.modules" %% "scala-async" % "0.9.2"
      ),
      jsCall := "Plot().run();",
      outputPath := "server/src/main/webapp/"
    )
  ).dependsOn(shared)

  lazy val server = Project(
    "server",
    file("./server"),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ jsManagerSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"),
        "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"),
      libraryDependencies ++= Seq(
        "com.lihaoyi" %%% "upickle" % "0.1.7",
        "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
        "com.lihaoyi" %% "autowire" % "0.1.2",
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      )
    )
  ).dependsOn(shared)
}