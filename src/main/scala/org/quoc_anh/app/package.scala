package org.quoc_anh.app

import org.apache.spark.sql.SparkSession

object app extends Serializable {

  lazy val spark: SparkSession = {
    SparkSession.builder().master("local").appName("spark session").getOrCreate()
  }

}
