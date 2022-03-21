
# SBT File
1. To run spark you will need spark-core, spark-catalyst, spark-sql and maybe spark-mllib.
2. To run delta you will need delta-core.
3. You can the above four repos as provided scope and test scope in maven or just provided and test as shown in the build.sbt.

# SBT Build
1. Run sbt clean
2. Run sbt compile
3. Run sbt package which should create a jar in target/scala-2.12

# Maven Build
1. Run mvn clean
2. Run mvn package which should create a jar in target

# Databricks Job
1. Run your databricks job as a jar job and set your main class to: `com.demo.deltademo.Driver`
2. This example has no arguments but you can consume arguments provided by the main args.