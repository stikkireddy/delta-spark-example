package com.demo.deltademo

import org.apache.spark.sql.SparkSession

object Driver {
  def main(args: Array[String]): Unit = {
    println("Hello from main also running optimize")
    import java.nio.file.Files
    val tempDir = Files.createTempDirectory("some-prefix")
    val tempDeltaLoc = tempDir.toString + "/test.delta"
    val spark = SparkSession.builder.appName("test").getOrCreate()
    spark.range(100).write.format("delta").save(tempDeltaLoc)
    spark.read.format("delta").load(tempDeltaLoc).show(10)
    try {
      spark.sql("OPTIMIZE delta.`" + tempDeltaLoc + "`")
      spark.sql("DESCRIBE HISTORY delta.`" + tempDeltaLoc + "`").show(numRows = 100, truncate = false)
      println("Clean up directory")
      tempDir.toFile.delete()
    } catch {
      case a: Exception =>
        println("Clean up directory")
        tempDir.toFile.delete()
        throw a
    }
  }
}

