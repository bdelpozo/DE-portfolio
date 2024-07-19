package de.portfolio
package structured.retries

import spark.{ErrorLevel, SparkSessionWrapper}

import org.apache.spark.sql.SparkSession

import java.sql.Timestamp

object SimulatedRetries extends App with SparkSessionWrapper{
  // it is possible to have falls in network while streaming so it's useful to set a retry protocol
  private val sessionBuilder = createSparkSession.withLogLevel(ErrorLevel)
    .withName("Simulated Retry Example")
    .withCores("local[2,6]") // threads, retries

  override implicit val spark: SparkSession = sessionBuilder.build

  import spark.implicits._

  spark.readStream
    .format("rate")
    .option("rowsPerSecond", "10")
    .option("numPartitions", "2")
    .load().as[(Timestamp, Long)]
    .map(nr => {
      if (nr._2 == 3) {
        throw new RuntimeException("nr 3 detected!")
      }
      nr._2
    })
    .writeStream
    //.trigger(Trigger.Continuous("2 second") this api is not too mature yet
    .format("console")
    .start()
    .awaitTermination()



}
