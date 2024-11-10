package com.miu.main;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main6 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main6")
                .master("local[*]")
                .getOrCreate();

        List<String> list = Arrays.asList("apple","orange","banana");

//        Dataset<String> ds = spark.createDataset(list, Encoders.STRING());
//
//        // Map operation
//        // Using flatMap
//        Dataset<String> charactersDS = ds
//                .flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split("")).iterator(),Encoders.STRING());
//
//        charactersDS.show();

        Dataset<Row> df = spark.createDataset(list, Encoders.STRING()).toDF("fruit");

        // Map operation
        // Using flatMap
//        Dataset<Row> charactersDS = df
//                .flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split("")).iterator(),Encoders.STRING());

        Dataset<Row> charactersDF = df.withColumn("characters", functions.explode(functions.split(df.col("fruit"), "")));
        Dataset<Row> fruits = charactersDF.drop("fruit");

        charactersDF.show();
        fruits.show();

        spark.close();
    }
}
