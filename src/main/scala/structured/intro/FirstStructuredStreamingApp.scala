package de.portfolio
package structured.intro

import spark.{ErrorLevel, SparkSessionWrapper}

import de.portfolio.structured.intro.StreamingHelper.{createConsoleSink, createSourceRateReader}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.streaming.{DataStreamReader, DataStreamWriter, StreamingQuery}

object StreamingHelper {

  // Next method creates rowsPerSecond registries per second and load the data into a DataFrame
  // A DataStreamReader reads streaming data
  def createSourceRateReader(partitionCount: Int, rowsPerSecond: Int = 50000)(implicit spark: SparkSession): DataFrame = {
    val readerSourceRate: DataStreamReader = spark.readStream
      .format("rate") // it generates a registry per second
      .option("rampUpTime", 1) // start-up time
      .option("numPartitions", partitionCount)
      .option("rowsPerSecond", rowsPerSecond)
    val dfSourceRate: DataFrame = readerSourceRate.load()
    dfSourceRate
  }

  // A DataStreamWriter writes streaming data
  // In this method we write data in console
  // OutputMode is the output mode of the data:
  // - append: appends the new data to the existing data.
  // - complete: rewrites the existing data with the new data, but no aggregations can be made.
  // - update: updates the existing data with the new data
  def createConsoleSink(dataFrame: DataFrame): StreamingQuery = {
    val writerSinkConsole: DataStreamWriter[Row] = dataFrame.writeStream
      .format("console")
      .outputMode("append")
      .option("truncate", "false")
      .queryName("RateToConsole")

    // A StreamingQuery manages a streaming job execution
    val queryStreamingToConsole: StreamingQuery = writerSinkConsole.start()
    queryStreamingToConsole
  }

}


object FirstStructuredStreamingApp extends App with SparkSessionWrapper {
  val partitionCount = 5
  private val sessionBuilder = createSparkSession.withLogLevel(ErrorLevel)
    .withName("FirstStructureStreamingApp")
    .withCores(s"local[$partitionCount]")
    .withAdaptativeDisabled
    .withShufflePartitions(partitionCount)
    .withCheckpointLocation("./checkpoint")
    .withDeleteCheckpointEnabled

  override implicit val spark: SparkSession = sessionBuilder.build
  // This is a simple Structured Streaming app. We use the two very useful methods of SteamingHelper Object
  // First method creates data in memory and the other one display it in console

  // First, we create a source
  val dfSourceRate = createSourceRateReader(partitionCount)


  // And then, we create a sink
  val queryStreamingToConsole = createConsoleSink(dfSourceRate)

  queryStreamingToConsole.awaitTermination()

  spark.stop()

  //  dfSourceRate (DataFrame) 5 secs -> queryStreamingToConsole (StreamingQuery) -> awaitTermination

}
