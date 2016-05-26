name := "Momentum_training_scala"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0"

mainClass in (Compile, run) := Some("YahooTickers")