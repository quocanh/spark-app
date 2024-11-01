package org.quoc_anh.app

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

trait TestWrapper {

  //http://stackoverflow.com/questions/27781187/how-to-stop-messages-displaying-on-spark-console
  // Doesn't work with scala 2.12.5 and spark 3.4.0
  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  sys.props("testing") = "true" // to indicate 'test' environment

}
