
ThisBuild / version := "0.5.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.8"




lazy val root = (project in file("."))
  .settings(
    name := "delta-spark-example",
    idePackagePrefix := Some("com.demo.deltademo"),
    libraryDependencies ++= providedSparkDependencies ++ testSparkDependencies ++ testDeps,
    scalacOptions += "-target:jvm-1.8",
    resolvers += "Apache Snapshots" at "https://repository.apache.org/snapshots/",
    Compile / unmanagedSourceDirectories +=
      baseDirectory.value / "src" / "main" / "shim" / "2",
    Test / unmanagedSourceDirectories +=
      baseDirectory.value / "src" / "test" / "shim" / "2",
  )

lazy val sparkVersion = "3.2.1"

lazy val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-catalyst" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "io.delta" %% "delta-core" % "1.1.0"
)

lazy val testSparkDependencies = sparkDependencies.map(_ % "test")

lazy val testDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
)

lazy val providedSparkDependencies = sparkDependencies.map(_ % "provided")

Compile / compileOrder := CompileOrder.JavaThenScala