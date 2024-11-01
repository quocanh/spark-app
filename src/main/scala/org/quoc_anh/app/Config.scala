package org.quoc_anh.app

import org.apache.spark.sql.SparkSession

object Config {

  lazy val spark: SparkSession = {
    SparkSession.builder().master("local").appName("spark session").getOrCreate()
  }

  lazy val env: String = detectEnvironment()
  lazy val config = {
    env match {
      case "production" => production_config
      case "test" => test_config
      case _ => dev_config
    }
  }

  // *********************************************************
  // Constants which are used only for keys in config maps
  val REPORT_S3_PATH = "report_s3_path"
  val DIMENSION_S3_PATH = "dim_s3_path"
  // ********************************************************

  // Various predefined paths and buckets
  val DEVELOPMENT_S3_BUCKET = "s3a://my-sandbox/philly"
  val PRODUCTION_S3_BUCKET = "s3a://my-prod/philly"
  val DEVELOPMENT_S3_DIMENSION_BUCKET = "s3a://my-sandboxs/philly/dim/"
  val PRODUCTION_S3_DIMENSION_BUCKET = "s3a://my-prod/philly/dim/"

  def detectEnvironment() = {
    val x: String = if (sys.props.get("testing") == Some("true")) {
      "test"
    } else if (spark.sparkContext.isLocal) {
      "development"
    } else {
      "production"
    }
    println(s"**********  $x environment detected **********")
    x
  }

  val dev_config: scala.collection.immutable.Map[String, Any] = scala.collection.immutable.Map(
    REPORT_S3_PATH -> DEVELOPMENT_S3_BUCKET,
    DIMENSION_S3_PATH -> DEVELOPMENT_S3_DIMENSION_BUCKET
  )

  val production_config: scala.collection.immutable.Map[String, Any] = scala.collection.immutable.Map(
    REPORT_S3_PATH -> PRODUCTION_S3_BUCKET,
    DIMENSION_S3_PATH -> PRODUCTION_S3_DIMENSION_BUCKET
  )

  val test_config: scala.collection.immutable.Map[String, Any] = scala.collection.immutable.Map(
    REPORT_S3_PATH -> "./src/test/resources/reports",
    DIMENSION_S3_PATH -> "./src/test/resources/dimensions/philly"
  )

  def get(key: String): Any = {

    val configResult = config.getOrElse(key, "")

    if (configResult == "") {
      throw new Exception(s"Could not resolve configuration key: $key")
    }

    configResult
  }

}