package de.portfolio
package spark

import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.apache.spark.sql.types.StructType
import org.scalatest.funsuite.AnyFunSuite


class SparkSqlAppTest extends AnyFunSuite with SparkSessionWrapper with DataFrameComparer{
  val df = spark.createDataFrame(mock.shops).toDF("name", "category", "num_employees")
// TODO arreglar esto, error de structfield, el mock tiene una columna nullable = false y el real es nullable = true

  //  test("Verify avgEmployeesNumPerCategory method") {
//    val dataIO = DataIO2(spark)
//    val result = dataIO.avgEmployeesNumPerCategory(df)
//    assertSmallDataFrameEquality(result, spark.createDataFrame(mock.avg_num_employees_category).toDF("category", "average_num_employees"))
//  }

//  test("Verify totalEmployeesNumPerCategory method") {
//    val dataIO = DataIO2(spark)
//    val result = dataIO.totalEmployeesNumPerCategory(df)
//    val d 1491ata = {
//      spark.createDataset(spark.sparkContext.parallelize(mock.total_employees_per_category), StructType(mock.schema_total))
//    }
//    assertSmallDatasetEquality(result, data)
//  }

  test("Verify employeesNumShopsRanking method") {
    val dataIO = DataIO2(spark)
    val result = dataIO.employeesNumShopsRanking(df)
    assertSmallDataFrameEquality(result, spark.createDataFrame(mock.ranking).toDF("name", "category", "num_employees", "rank"))
  }
}

