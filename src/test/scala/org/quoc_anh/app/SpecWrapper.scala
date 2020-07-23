package org.quoc_anh.app

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

trait SpecWrapper {

  //http://stackoverflow.com/questions/27781187/how-to-stop-messages-displaying-on-spark-console
  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  sys.props("testing") = "true" // to indicate 'test' environment

}
