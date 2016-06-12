name := "Momentum_scala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"

  Seq(
    "org.scalaj" %% "scalaj-http" % "2.3.0",
    "org.json4s" %% "json4s-native" % "3.3+",
    "org.json4s" %% "json4s-ext" % "3.3+",

    "io.spray" %% "spray-can" % sprayV withSources() withJavadoc(),
    "io.spray" %% "spray-routing" % sprayV withSources() withJavadoc(),
    "io.spray" %% "spray-json" % "1.3.1",
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    "org.scalaz" %% "scalaz-core" % "7.1.0"
  )
}