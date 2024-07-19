package de.portfolio
package spark

import implicits.Implicits._
import org.apache.spark.sql.SparkSession



// Sometimes traits are used for some specific functionalities.
// We are creating a SparkSession manually all the time, let's implement a trait that help us to do so

sealed trait LogLevel {
  def level: String
}

case object ErrorLevel extends LogLevel {
  override def level: String = "ERROR"
}

case object WarnLevel extends LogLevel {
  override def level: String = "WARN"
}

case object InfoLevel extends LogLevel {
  override def level: String = "INFO"
}

case object DebugLevel extends LogLevel {
  override def level: String = "DEBUG"
}

object Log {
  def level(logLevel: LogLevel): Unit = {
    // You can call your 'setLogLevel' function here
    println(s"Setting log level to ${logLevel.level}")
  }
}

trait SparkSessionWrapper {
  implicit val spark: SparkSession = createSparkSession.withLogLevel(InfoLevel).build
  class SessionBuilder {
    private var logLevel = "ERROR"
    private var coresW = "local[*]"
    private var driverMemory: Memory   = 1.Gb
    private var executorMemory: Memory = 1.Gb
    private var driverCores   = 1
    private var executorCores = 1
    private var appName = "spark session"
    private var offHeapEnabled = false
    private var offHeapGbSize: Memory = 0.Gb
    private var hiveSupportEnabled = false
    private var deltaLakeSupportEnabled = false
    private var shufflePartitionsTuned = false
    private var adaptativeDisabled = true
    private var checkpointLocation = "./checkpoint"
    private var deleteCheckpointLocation = false
    private var shufflePartitions = 1

    def withLogLevel(level: LogLevel): SessionBuilder = {
      logLevel = level.level
      this
    }
    def withCores(cores: String): SessionBuilder = {
      coresW = cores
      this
    }
    def withDriverMemory(memory: Memory): SessionBuilder = {
      driverMemory = memory
      this
    }
    def withExecutorMemory(memory: Memory): SessionBuilder = {
      executorMemory = memory
      this
    }
    def withDriverCores(cores: Int): SessionBuilder = {
      driverCores = cores
      this
    }
    def withExecutorCores(cores: Int): SessionBuilder = {
      executorCores = cores
      this
    }
    def withName(name: String): SessionBuilder = {
      appName = name
      this
    }
    def withOffHeapEnabled: SessionBuilder = {
      offHeapEnabled = true
      this
    }
    def withOffHeapGbSize(size: Memory): SessionBuilder = {
      offHeapGbSize = size
      this
    }
    def withEnableHiveSupport: SessionBuilder = {
      hiveSupportEnabled = true
      this
    }
    def withDeltaLakeSupport: SessionBuilder = {
      deltaLakeSupportEnabled = true
      this
    }

    def withTunedShufflePartitions: SessionBuilder = {
      shufflePartitionsTuned = true
      this
    }
    def withAdaptativeDisabled: SessionBuilder = {
      adaptativeDisabled = false
      this
    }

    def withCheckpointLocation(location: String): SessionBuilder = {
      checkpointLocation = location
      this
    }

    def withDeleteCheckpointEnabled: SessionBuilder = {
      adaptativeDisabled = true
      this
    }
    def withShufflePartitions(partitions: Int): SessionBuilder = {
      shufflePartitions = partitions
      this
    }

    private def buildSparkSession(appName: String): SparkSession = {
      var builder: SparkSession.Builder = SparkSession
        .builder()
        .master(coresW)
        .appName(appName)
        .config("spark.driver.memory", driverMemory.toString)
        .config("spark.executor.memory", executorMemory.toString)
        .config("spark.driver.cores", driverCores.toString)
        .config("spark.executor.cores", executorCores.toString)
        // set off Spark-ui to avoid problem in testing
        .config("spark.ui.enabled", "false")
        .config("spark.sql.streaming.checkpointLocation", "./checkpoint")
        .config("spark.sql.shuffle.partitions", shufflePartitions.toString)

      if (offHeapEnabled) {
        builder = builder.config("spark.memory.offHeap.enabled", "true")
        builder = builder.config("spark.memory.offHeap.size", offHeapGbSize.toString)
      }

      if (hiveSupportEnabled) {
        builder = builder.enableHiveSupport()
      }
      if (deltaLakeSupportEnabled) {
        builder = builder.config("spark.sql.extensions", "io.delta.sql.DeltaSparkSessionExtension")
        builder = builder.config("spark.sql.catalog.spark_catalog", "org.apache.spark.sql.delta.catalog.DeltaCatalog")
      }

      if (shufflePartitionsTuned) {
        val cores = Runtime.getRuntime.availableProcessors
        builder = builder.config("spark.shuffle.partitions", cores.toString)
      }

      if (adaptativeDisabled) {
        builder = builder.config("spark.sql.adaptive.enabled", "false")
      }

      if (deleteCheckpointLocation) {
        builder = builder.config("spark.sql.streaming.forceDeleteTempCheckpointLocation", "true")
      }

      builder.getOrCreate()
    }

    def build: SparkSession = {
      val session = buildSparkSession("spark session")
      setLogLevel(session, logLevel)
      session
    }
    def build(name: String): SparkSession = {
      val session = buildSparkSession(name)
      setLogLevel(session, logLevel)
      session
    }
  }
  def createSparkSession: SessionBuilder = new SessionBuilder
  private def setLogLevel(spark: SparkSession, logLevel: String): Unit = spark.sparkContext.setLogLevel(logLevel)
}
