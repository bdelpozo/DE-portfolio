package de.portfolio
package spark

import de.portfolio.spark.FirstSparkApp.{case2PassCondition, df, exam1PassCondition, exam2PassCondition, spark}
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.{avg, col, map_concat}
import org.apache.spark.sql.{Column, DataFrame, SaveMode, SparkSession}


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
  // .master("local[*]") configures where it's going to be executed, and * means cores are needeed but we could specify a number of core we want to use

  val spark = SparkSession.builder().master("local[*]")
    .appName("PrimeraAppSpark").getOrCreate()

  // setLogLevel
  spark.sparkContext.setLogLevel("ERROR")

  import spark.implicits._
  // First let's use generateData to generate data to use our Spark App
  val data: Seq[(String, Int, Int)] = FirstSparkAppDataGen.generateData(2000)
  val df: DataFrame = data.toDF("name", "mark1", "mark2")

  println("Publicated Marks:")
  df.show(truncate = false)

  val exam1PassCondition = col("mark1") > 70
  val exam2PassCondition = col("mark2") > 65

  val passedFirstExam: DataFrame = df.filter(exam1PassCondition)
  println("Passed first exam:")
  passedFirstExam.show(truncate=false)
  val passedSecondExam: DataFrame = df.filter(exam2PassCondition)
  println("Passed second exam:")
  passedSecondExam.show(truncate = false)

  // Case 1: It is needed to pass both exams to pass
  val passedCase1: DataFrame = df.filter(exam1PassCondition && exam2PassCondition)
  println("Passed both exams:")
  passedCase1.show(truncate=false)

  // Case 2: If the average mark is passed, you passed
  val case2PassCondition = (col("mark1") + col("mark2"))/2 > 67
  val passedCase2: DataFrame = df.filter(case2PassCondition)
  println("Average mark is passed:")
  passedCase2.show(truncate=false)

  // Now we can write results in a csv file
  passedCase1.write.mode(SaveMode.Overwrite)
    .csv("out/passedCase1.csv")

  passedCase2.write.mode(SaveMode.Overwrite)
    .csv("out/passedCase2.csv")

  // In order to see easily if the pupil has passed, we are going to add a new column more readable
  println("more readable results")
//  val df_nuevo: DataFrame = df.withColumn("results", col("mark1"))
  // TODO map con las notas aprobado y suspenso en otra columna

  println("Average mark for exam 1")
  df.agg(avg("mark1").alias("avg_mark_exam_1")).show()

  println("Average mark for exam 2")
  df.agg(avg("mark2").alias("avg_mark_exam_2")).show()

  // and then stop the Spark App
  spark.stop()
  System.exit(0)

}

case class DataIO(spark: SparkSession) {

  // We could also define functions to do the filters:
  def passedCase1M(df: DataFrame): DataFrame = {
    df.filter(col("mark1") > 70 && col("mark2") > 65)
  }
  def passedCase2M(df: DataFrame): DataFrame = {
    df.filter((col("mark1") + col("mark2"))/2 > 67)
  }
  // and now we can test this methods

}
