name := "Momentum_scala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.json4s" %% "json4s-native" % "3.3+",
  "org.json4s" %% "json4s-ext" % "3.3+"
)

mainClass in (Compile, run) := Some("YahooTickers")