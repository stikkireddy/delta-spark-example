
# SBT File
1. To run spark you will need spark-core, spark-catalyst, spark-sql and maybe spark-mllib.
2. To run delta you will need delta-core.
3. You can the above four repos as provided scope and test scope in maven or just provided and test as shown in the build.sbt.
```scala

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

lazy val root = (project in file("."))
  .settings(
    ...
    libraryDependencies ++= providedSparkDependencies ++ testSparkDependencies ++ testDeps,
    ...
  )
```

# SBT Build
1. Run sbt clean
2. Run sbt compile
3. Run sbt package which should create a jar in target/scala-2.12

# Maven pom file

Here is an example of the dependencies: 
```xml
<dependencies>
  ...
    <dependency>
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-core_2.12</artifactId>
      <version>3.2.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-catalyst_2.12</artifactId>
      <version>3.2.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-sql_2.12</artifactId>
      <version>3.2.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.spark</groupId>
      <artifactId>spark-mllib_2.12</artifactId>
      <version>3.2.1</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>io.delta</groupId>
      <artifactId>delta-core_2.12</artifactId>
      <version>1.1.0</version>
      <scope>provided</scope>
    </dependency>
  ...   
</dependencies>
```

# Maven Build
1. Run mvn clean
2. Run mvn package which should create a jar in target

# Databricks Job
1. Run your databricks job as a jar job and set your main class to: `com.demo.deltademo.Driver`
2. This example has no arguments but you can consume arguments provided by the main args.