name := """scala-interview"""
val AkkaVersion = "2.6.8"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % AkkaVersion
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test
lazy val commonSettings = Seq(
  organization := "com.particeep",
  version := "1.1.0",
  scalaVersion := "2.13.5",
  resolvers ++= Seq(
    Resolver.jcenterRepo,
    Resolver.mavenCentral
  ),
  libraryDependencies ++= (deps_tests),
  updateOptions := updateOptions.value.withCachedResolution(true),
  sources in (Compile, doc) := Seq.empty
)

lazy val root: Project = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)

lazy val deps_tests = Seq(
  "org.scalatestplus"       %% "mockito-3-4"        % "3.2.6.0" % Test withSources (),
  "org.scalatestplus.play"  %% "scalatestplus-play" % "5.1.0"   % Test withSources () excludeAll (ExclusionRule(
    organization = "org.mockito"
  )),
  "com.opentable.components" % "otj-pg-embedded"    % "0.13.3"  % Test withSources ()
)
