package de.portfolio
package spark

import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object mock {
  val shops: Seq[(String, String, Int)] = Seq(
    ("Shop1", "tech", 3),
    ("Shop2", "pets", 8),
    ("Shop3", "pets", 7),
    ("Shop4", "sports", 6),
    ("Shop5", "tech", 6),
    ("Shop6", "tech", 7),
    ("Shop7", "sports", 7),
    ("Shop8", "pets", 1),
    ("Shop9", "tech", 7),
    ("Shop10", "pets", 3)
  )
//val df_shops = spark.createDataFrame(shops).toDF("name", "category", "total_employees_per_category")

  val total_employees_per_category: Seq[(String, Long)] = Seq(
    ("sports", 13),
    ("pets", 19),
    ("tech", 23)
  )
  val schema_total = List(StructField("category",StringType,true), StructField("total_employees_category",LongType,true))
//  val df_total_employees_per_category = spark.createDataFrame(total_employees_per_category).toDF("category", "total_employees_per_category")

  val avg_num_employees_category: Seq[(String, Double)] = Seq(
    ("pets", 4.75),
    ("tech", 5.75),
    ("sports", 6.5)
  )
//val df_avg_num_employees_category = spark.createDataFrame(avg_num_employees_category).toDF("category", "average_num_employees")

  val ranking: Seq[(String, String, Int, Int)] = Seq(
    ("Shop2", "pets", 8, 1),
    ("Shop3", "pets", 7, 2),
    ("Shop10", "pets", 3, 3),
    ("Shop8", "pets", 1, 4),
    ("Shop7", "sports", 7, 1),
    ("Shop4", "sports", 6, 2),
    ("Shop6", "tech", 7, 1),
    ("Shop9", "tech", 7, 1),
    ("Shop5", "tech", 6, 3),
    ("Shop1", "tech", 3, 4)
  )
}
//val df_ranking = spark.createDataFrame(ranking).toDF("name", "category", "num_employees", "rank")