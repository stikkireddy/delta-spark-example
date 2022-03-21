package com.demo.deltademo

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.scalatest._

class TestBase extends FunSuite with BeforeAndAfterAllConfigMap {
  var spark: SparkSession = _

  override def beforeAll(cm: ConfigMap) :Unit = {
    val conf = new SparkConf()
      //      .setAppName("dbxapp")
      .setMaster("local")
      .set("spark.default.parallelism", "1")

    spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()
  }

  override def afterAll(cm: ConfigMap) :Unit  = {
    spark.close
  }
}

class DriverTestSuite extends TestBase {

  test("Dbx simple test") {
    Driver.main(null)
  }
}
