name := "Momentum_scala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"

  Seq(
    "org.scala-lang" % "scala-compiler" % "2.11.8",
    "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5",

    "org.scalaj" %% "scalaj-http" % "2.3.0",
    "org.json4s" %% "json4s-native" % "3.3+",
    "org.json4s" %% "json4s-jackson" % "3.3+",
    "org.json4s" %% "json4s-ext" % "3.3+",

    "io.spray" %% "spray-can" % sprayV withSources() withJavadoc(),
    "io.spray" %% "spray-routing" % sprayV withSources() withJavadoc(),
    "io.spray" %% "spray-json" % "1.3.1",
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    "org.scalaz" %% "scalaz-core" % "7.1.0",

    "org.scalactic" %% "scalactic" % "3.0.0",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
}