package com.miu.main;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.Collections;

public class Main3 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main3")
                .master("local[*]")
                .getOrCreate();


        Dataset<Row> studentCsv = spark.read().option("header", true).csv("src/main/resources/csv/student_data.csv");

        studentCsv.write().mode(SaveMode.Overwrite).parquet("src/main/resources/parquet/student_data.parquet");


        Dataset<Row> studentParquet = spark.read().parquet("src/main/resources/parquet/student_data.parquet");
        studentParquet.show();
        studentParquet.printSchema();

        Dataset<Row> fill = studentParquet.na().replace("score", Collections.singletonMap("null", "22"));
        fill.show();
        fill.printSchema();

        Dataset<Row> sum = fill.groupBy("student_name").count();
        sum.show();


        spark.close();
    }
}
