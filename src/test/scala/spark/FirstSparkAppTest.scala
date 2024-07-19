package de.portfolio
package spark

import spark.FirstSparkApp.passedCase1
import org.scalatest.funsuite.AnyFunSuite
import com.github.mrpowers.spark.fast.tests.DataFrameComparer

class FirstSparkAppTest extends AnyFunSuite with SparkSessionWrapperTest with DataFrameComparer {

  test("Verify passedCase1 method") {
    val data = Seq(("Name1", 43, 86), ("Name2", 87, 78), ("Name3", 43, 86))
    val df = spark.createDataFrame(data).toDF("name", "mark1", "mark2")
    val dataIO = DataIO(spark)
    val result = dataIO.passedCase1M(df)
    assert(result.count() == 1)
    assertSmallDataFrameEquality(result, spark.createDataFrame(Seq(("Name2", 87, 78))).toDF("name", "mark1", "mark2"))
  }

  test("Verify passedCase2 method") {
    val data = Seq(("Name1", 43, 86), ("Name2", 87, 78), ("Name3", 58, 86))
    val df = spark.createDataFrame(data).toDF("name", "mark1", "mark2")
    val dataIO = DataIO(spark)
    val result = dataIO.passedCase2M(df)
    assert(result.count() == 2)
    assertSmallDataFrameEquality(result, spark.createDataFrame(Seq(("Name2", 87, 78), ("Name3", 58, 86))).toDF("name", "mark1", "mark2"))
  }
}
