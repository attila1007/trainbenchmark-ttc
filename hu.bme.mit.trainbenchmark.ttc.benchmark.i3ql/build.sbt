name := "szakdoga"

version := "1.0"

scalaVersion := "2.10.2"

scalaOrganization := "org.scala-lang.virtualized"

libraryDependencies ++= Seq(
  "de.tud.cs.st" %% "idb-syntax-iql" % "latest.integration",
  "de.tud.cs.st" %% "idb-runtime" % "latest.integration"
)
