organization := "ch.usi.inf.reveal.parsing"

name := "stormed-tutorial"
version := "2.0.0-SNAPSHOT"
scalaVersion := "2.12.3"

val nexus = "https://rio.inf.usi.ch/nexus/"
val nexusReleases  = "releases" at nexus + "repository/maven-releases/"
val nexusSnapshots = "snapshots" at nexus + "repository/maven-snapshots/"
val nexusPublic	   = "releases" at nexus + "repository/maven-public/"

publishMavenStyle := true

resolvers += "StORMeD Dev-Kit Repository" at "https://stormed.inf.usi.ch/releases/"
credentials += Credentials("Sonatype Nexus Repository Manager", "stormed.inf.usi.ch", "anonymous", "anonymous")
															
val compileDependencies = List(
	"ch.usi.inf.reveal.parsing"					%% "stormed-devkit"	% "2.0.0",
    "org.scalaj"                                %% "scalaj-http" % "2.3.0",
	"raykernel"                                 % "code-readability" % "1.0"
)

libraryDependencies ++= compileDependencies
