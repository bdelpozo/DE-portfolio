package de.portfolio
package spark

import spark.SparkSessionWrapper

import de.portfolio.spark.SparkSqlApp.df
import org.apache.spark.sql.catalog.Catalog
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, col, desc, rank}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession, functions}

object SparkSQLAppDataGen {

  val maxEmployees = 9
  def generateData2(numRecords: Int, categories: List[String]): Seq[(String, String, Int)]  = {
    val data = (1 to numRecords).map { i =>
      val name = s"Shop$i"
      val category = categories(scala.util.Random.nextInt(categories.size))
      val numEmployees = scala.util.Random.between(1, maxEmployees)
      (name, category, numEmployees)
    }
    data
  }
}


object SparkSqlApp extends App with SparkSessionWrapper {

  // From now on, we are going to use SparkSessionWrapper to start our Spark's sessions


  spark.sparkContext.setLogLevel("ERROR")
  import spark.implicits._
  // if we have to read some data from an external file, you can do this:
  val df1: DataFrame = spark.read
    .option("inferSchema", "true")
    .json("src/main/resources/sample-data/shopping_centre_data.json")
  df1.printSchema()
  df1.show(truncate = false)


  // To play with more data, we are going to create it and transform it to a DataFrame.
  val data = SparkSQLAppDataGen.generateData2(10, List("sports", "supermarket", "tech", "clothes", "pets"))
  val df = data.toDF("name", "category", "num_employees")
  df.show(truncate = false)

  // And we have usual sql operations to manage databases and tables. Usually it's needed to have a real table
  // to do a query so we are going to create temporal views to do so

  df.createOrReplaceTempView("shopping_centre_data")

   // To list Databases and Tables:
  spark.catalog.listDatabases().show(truncate = false)
  spark.catalog.listTables().show(truncate = false)
  print(s"Current Database is: ${spark.catalog.currentDatabase}")
  // if shopping-centre database does not exist we create it and set it to current
  if (!spark.catalog.databaseExists("shoppingcentre")) {
    spark.sql("CREATE DATABASE shoppingcentre")
  }
  spark.catalog.setCurrentDatabase("shoppingcentre")
  println()
  println(s"Current Database is: ${spark.catalog.currentDatabase}")
  println()
  // to have more information about tables:
  val catalog: Catalog = spark.catalog
  catalog.listTables().collect().foreach { table =>
    println(table)
    println(s" -- Tabla: ${table.name}")
    catalog.listColumns(table.name).collect().foreach( column =>
      println("    - " + column)
    )
  }
  // and we drop the database
  spark.sql("DROP DATABASE shoppingcentre CASCADE")

  // this database with its elements are stored in a directory called spark-warehouse

  // To know the total employees for category of shops we could do it by two different methods:
  println("Total employees per category")
  val result1 = spark.sql("SELECT category, sum(num_employees) as total_employees_category FROM shopping_centre_data GROUP BY category")
  result1.show()
  val result1_1 = df.groupBy("category").agg(functions.sum("num_employees").alias("total_employees_category")).orderBy("total_employees_category")
  result1_1.show()
  println("tipos" ,result1_1.schema)

  // Now we could continue doing operations like the following:
  // to know the average number of employees per shop:
  println("Average number of employees per category")
  df.groupBy("category").agg(avg("num_employees").alias("average_num_employees")).orderBy("average_num_employees").show()

  // window operations: we can use lag, lead, rank, dense_rank, row_number... and all the window operations known in sql
  // to see a ranking of shops with more number of employees per category

  val windowSpec = Window.partitionBy("category").orderBy(desc("num_employees"))
  val result2 = df.withColumn("rank", rank().over(windowSpec))
  val shops_more_employes_condition = col("rank") <2
  println("Ranking of shops with more employees per category")
  result2.show()
  val result2_filtered: DataFrame = result2.filter(shops_more_employes_condition)
  println("Shops with more employees per category")
  result2_filtered.show()
  // if more than one result appears for each category it means that there are two or more shops with the same number of employees
  // and it's the maximum in that category

  spark.stop()

}

case class DataIO2(spark: SparkSession) {

  // We could also define functions to do the filters:
  def avgEmployeesNumPerCategory(df: DataFrame): Dataset[Row] = {
    df.groupBy("category").agg(avg("num_employees").alias("average_num_employees")).orderBy("average_num_employees")
  }

  def totalEmployeesNumPerCategory(df: DataFrame): Dataset[Row]= {
    df.groupBy("category").agg(functions.sum("num_employees").alias("total_employees_category")).orderBy("total_employees_category")
  }

  def employeesNumShopsRanking(df: DataFrame): DataFrame = {
    val windowSpec = Window.partitionBy("category").orderBy(desc("num_employees"))
    val result = df.withColumn("rank", rank().over(windowSpec))
    result
  }
  // and now we can test this methods

}
