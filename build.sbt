
//ThisBuild / version := "0.1.0-SNAPSHOT"
//
//ThisBuild / scalaVersion := "2.13.14"

name := "DE-portfolio"
version:= "1.0.0"
scalaVersion:= "2.13.14"
idePackagePrefix := Some("de.portfolio")

val VersionSpark = "3.5.1"
//val VersionCatsCore = "2.12.0"
val VersionScalaTest = "3.2.18"
val VersionScalafmt = "3.8.1"

//val sparkDependencies = Seq(
//  "org.apache.spark" %% "spark-core" % VersionSpark % Provided,
//  "org.apache.spark" %% "spark-sql" % VersionSpark % Provided,
//  "org.apache.spark" %% "spark-graphx" % VersionSpark % Provided,
//)

//val catsDependencies = Seq(
//  "org.typelevel" %% "cats-core" % VersionCatsCore
//  // Cats relies on improved type inference via the fix for SI-2712, which is not enabled by default.
//  // For Scala 2.12 you should add the following to your build.sbt:
//  // scalacOptions += "-Ypartial-unification"
//)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % VersionScalaTest % "test",
  "com.github.mrpowers" %% "spark-fast-tests" % "1.3.0" % "test"
)

val jmhDependencies = Seq(
  "org.openjdk.jmh" % "jmh-core" % "1.37",
  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.37",
)

val scalaFmtDependencies = Seq(
  "org.scalameta" %% "scalafmt-sysops" % VersionScalafmt,
  "org.scalameta" %% "scalafmt-dynamic" % VersionScalafmt,
)

// Spark Testing
//Test / fork := true
//Test / parallelExecution := false
//
//libraryDependencies ++= sparkDependencies ++ catsDependencies ++ testDependencies ++
//  jmhDependencies ++ scalaFmtDependencies

// Add JVM Options
javaOptions ++= Seq(
  "-Xmx2G",
  "-Xms2G",
  "-XX:ReservedCodeCacheSize=512M",
  "-XX:+UseG1GC",
  "-XX:MaxGCPauseMillis=200",
  "-XX:G1ReservePercent=15",
  "-XX:InitiatingHeapOccupancyPercent=25",
  "-XX:+UseStringDeduplication"
)
// build.sbt
enablePlugins(JmhPlugin)
// Add JMH alias: -i 3 means 3 iterations, -wi 2 means 2 warm-up iterations, -f1 means 1 fork, -t1 means 1 thread
addCommandAlias("jmh", "Jmh/run -i 3 -wi 2 -f1 -t1")
// Add JMH alias with profiler options: -prof gc means garbage collection profiler
// -prof cl means class loading profiler
// -prof comp means compiler profiler
// -prof hs_gc means HotSpot garbage collection profiler
// -prof hs_cl means HotSpot class loading profiler
// -prof hs_comp means HotSpot compiler profiler
addCommandAlias("prof", "Jmh/run -i 3 -wi 2 -f1 -t1 -prof gc")
addCommandAlias("prof-cl", "Jmh/run -i 3 -wi 2 -f1 -t1 -prof cl")
addCommandAlias("prof-comp", "Jmh/run -i 3 -wi 2 -f1 -t1 -prof comp")
addCommandAlias("prof-gc", "Jmh/run -i 3 -wi 2 -f1 -t1 -prof gc")
// All the profilers
addCommandAlias("prof-all", "Jmh/run -i 3 -wi 2 -f1 -t1 -prof gc -prof cl -prof comp")

//lazy val root = (project in file("."))
//  .settings(
//    name := "DE-portfolio"
//  )
