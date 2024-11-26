package com.miu.main.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;

public class Main10 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main10")
                .master("local[*]")
                .getOrCreate();

        spark.udf().register(
                "hasPassed",
                (String grade) -> {
                    if (grade.equals("A"))
                        return "YES";
                    else return "NO";
                },
                DataTypes.StringType);


        Dataset<Row> examResult = spark.read().option("header", true).csv("src/main/resources/csv/student_exam_data.csv");

        examResult.show();

        // Dataset<Row> pass = examResult.withColumn("pass", functions.lit(examResult.col("grade").equalTo("A")? "YES":"NO"));
      //  Dataset<Row> pass = examResult.withColumn("pass", functions.when(examResult.col("grade").equalTo("A"), "YES").otherwise("NO"));
      //  Dataset<Row> pass = examResult.withColumn("pass", functions.call_function("hasPassed",examResult.col("grade")));
        Dataset<Row> pass = examResult.withColumn("pass", functions.call_udf("hasPassed",examResult.col("grade")));


        pass.show();


        spark.close();
    }
}
