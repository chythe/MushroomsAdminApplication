name := "MushroomsAdminApplication"

version := "1.0"

scalaVersion := "2.12.2"

// general package information (can be scoped to Windows)
maintainer := "Mateusz Chudy <matechu268@student.polsl.pl>"
packageSummary := "test-windows"
packageDescription := """Test Windows MSI."""

// wix build information
wixProductId := "ce07be71-510d-414a-92d4-dff47631848a"
wixProductUpgradeId := "4552fb0e-e257-4dbd-9ecb-dba9dbacf424"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

enablePlugins(JavaAppPackaging)
enablePlugins(WindowsPlugin)

libraryDependencies ++= {
  val akkaVersion = "2.5.1"
//  val sprayVersion = "1.3.3"
  val scalafxmlVersion = "0.3"
  val scalafxVersion = "8.0.92-R10"
  val scalajVersion = "2.3.0"
  val liftwebVersion = "3.1.0-M3"
  val gsonVersion = "2.8.1"
  Seq(
    "net.liftweb" %% "lift-json" % liftwebVersion,
    "org.scalaj" %% "scalaj-http" % scalajVersion,
//    "io.spray" % "spray-httpx_2.11" % sprayVersion,
//    "io.spray" % "spray-client_2.11" % sprayVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
//    "io.spray" %%  "spray-json" % sprayVersion,
    "org.scalafx" %% "scalafxml-core-sfx8" % scalafxmlVersion,
    "org.scalafx" %% "scalafx" % scalafxVersion,
    "com.google.code.gson" % "gson" % gsonVersion,
    "io.github.cloudify" %% "spdf" % "1.4.0"
  )
}