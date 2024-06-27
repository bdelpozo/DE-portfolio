package de.portfolio
package spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object FirstSparkAppConf {

  // Basic Spark configuration, driver, executors...

  val sparkConfLocal: SparkConf = new SparkConf()
    .setAppName("FirstSparkAppConf").setMaster("local[*]")
    .set("spark.shuffle.partitions", "5")
    .set("spark.sql.shuffle.partitions", "5").set("spark.driver.memory", "2g")
    .set("spark.driver.cores", "1").set("spark.executor.memory", "2g")
    .set("spark.executor.cores", "2").set("spark.executor.instances", "2")

  val sparkConfTesting: SparkConf = new SparkConf().setAppName("FirstSparkAppConf")
    .setMaster("local[2]").set("spark.shuffle.partitions", "5")
    .set("spark.sql.shuffle.partitions", "5").set("spark.driver.memory", "512m")
    .set("spark.driver.cores", "1").set("spark.executor.memory", "512m")
    .set("spark.executor.cores", "1").set("spark.ui.enabled", "false")
    .set("spark.sql.autoBroadcastJoinThreshold", "-1")
    .set("spark.dirs", "/tmp/spark-temp")
}

object FirstSparkAppDataGen {
  private val maxMark = 100
  // let's prepare random data with names and exam marks
  def generateData(numRecords: Int): Seq[(String, Int, Int)] = {
    val data = (1 to numRecords).map { i =>
      val name = s"Name$i"
      val mark1 = scala.util.Random.nextInt(maxMark)
      val mark2 = scala.util.Random.nextInt(maxMark)
      (name, mark1, mark2)
    }
    data
  }
}

object FirstSparkApp extends App {
  // SparkSession builder helps us to create a SparkSession. Simple Spark can be create like bellow
  // .master("local[*]") configures where it's going to be executed, and * means cpus needed but we could specify a number of cpu

  val spark = SparkSession.builder().master("local[*]")
    .appName("PrimeraAppSpark").getOrCreate()

  import spark.implicits._
  // First let's use generateData to generate data to use our Spark App
  val data: Seq[(String, Int, Int)] = FirstSparkAppDataGen.generateData(2000)
  val df: DataFrame = data.toDF("name", "mark1", "mark2")

  println("Publicated Marks:")
  df.show(truncate = false)

  val passedFirstExam: DataFrame = df.filter($"mark1" > 70)
  println("Passed first exam:")
  passedFirstExam.show(truncate=false)
  val passedSecondExam: DataFrame = df.filter($"mark2" > 65)
  println("Passed second exam:")
  passedSecondExam.show(truncate = false)

  // Case 1: It is needed to pass both exams to pass
  val passedCase1: DataFrame = df.filter($"mark1" > 70 && $"mark2" > 65 )
  println("Passed both exams:")
  passedCase1.show(truncate=false)

  // Case 2: If the average mark is passed, you passed
  val passedCase2: DataFrame = df.filter(($"mark1"+$"mark2")/2 > 67)
  println("Average mark is passed:")
  passedCase2.show(truncate=false)

  // Now we can write results in a csv file
  passedCase1.write.mode(SaveMode.Overwrite)
    .csv("out/passedCase1.csv")

  passedCase2.write.mode(SaveMode.Overwrite)
    .csv("out/passedCase2.csv")

  // and then stop the Spark App
  spark.stop()
}

