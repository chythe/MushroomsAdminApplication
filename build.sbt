name := "MushroomsAdminApplication"

version := "1.0"

scalaVersion := "2.12.2"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= {
  val akkaVersion = "2.5.1"
  val sprayVersion = "1.3.3"
  val scalafxVersion = "0.3"
  Seq(
    "io.spray" % "spray-httpx_2.11" % sprayVersion,
    "io.spray" % "spray-client_2.11" % sprayVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %%  "spray-json" % sprayVersion,
    "org.scalafx" %% "scalafxml-core-sfx8" % scalafxVersion
  )
}