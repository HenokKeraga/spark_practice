package com.miu.daily.nov10;

import org.apache.spark.sql.*;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.lit;

//Create a DataFrame with sample data and add a new column that is the result of a calculation on an existing column.
public class Main1 {

    public static void main(String[] args) {

        SparkSession sparkSession = SparkSession.builder()
                .appName("main1")
                .master("local[*]")
                .getOrCreate();


        List<Tuple2<Integer, Integer>> input = Arrays.asList(new Tuple2<>(1, 2), new Tuple2<>(3, 4), new Tuple2<>(5, 6));

//        Dataset<Row> dataFrame = sparkSession.createDataFrame(input, Integer.class);
//
//        dataFrame.show();

        UDF1<Integer, String> ageCategoryUDF = age -> {
            if (age <= 18) {
                return "Minor";
            } else if (age <= 30)
                return "Adult";

            return "Senior";
        };
        sparkSession.udf().register("ageCategoryUDF", ageCategoryUDF, DataTypes.StringType);
        Dataset<Row> dataset = sparkSession
                .createDataset(input, Encoders.tuple(Encoders.INT(), Encoders.INT()))
                .toDF("price1", "price2");
        dataset = dataset
                .withColumn("Total", col("price1").plus(col("price2")))
                .withColumn("concat", functions.concat(functions.lit("high"), lit("_"), col("price1")))
                .withColumn("Total2", functions.expr("price1 + price2"))
                .withColumn("isExpensive", functions.expr("CASE WHEN Total > 4 THEN 'YES' ELSE 'NO' END"))
                .withColumn("Age", col("price1").multiply(7))
                .withColumn("age_category",
                        functions.when(col("Age").leq(18), "Minor")
                                .when(col("Age").between(18, 30), "Adult")
                                .otherwise("Senior")
                )// using when  and otherwise function
                .withColumn("Age_category_2",
                        functions.expr("CASE WHEN Age <= 18 THEN 'Minor' WHEN Age > 18 AND Age <= 30 THEN 'Adult' ELSE 'Senior' END")
                )//using expr functions
                .withColumn(
                        "Age_category_3",
                        functions.udf(ageCategoryUDF, DataTypes.StringType).apply(col("age"))
                ); // using UDF functions
        dataset.show();

        Dataset<Row> dataset1 = dataset.selectExpr(
                "Age", "CASE WHEN Age <= 18 THEN 'Minor' WHEN Age > 18 AND Age <= 30 THEN 'Adult' ELSE 'Senior' END AS Category_age_3"
        ); // using selectExpr function
        dataset1.show();

        sparkSession.stop();
    }
}
