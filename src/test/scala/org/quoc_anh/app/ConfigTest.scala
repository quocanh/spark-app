package org.quoc_anh.app

import org.scalatest.FunSpec

class ConfigTest
  extends FunSpec
    with TestWrapper {

  describe("get") {

    it("returns a default config") {
      val actual = Config.get(Config.REPORT_S3_PATH)
      assert(actual == "./src/test/resources/reports")
    }

  }

  describe("create dataframe") {

    import app.spark.implicits._

    it("create a dataframe and show it") {
      println("Spark version = " + app.spark.version)
      val df = Seq((1,"a")).toDF("col1", "col2")
      df.show
      assert(1 == 1)
    }

  }
}
