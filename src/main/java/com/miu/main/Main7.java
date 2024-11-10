package com.miu.main;

import org.apache.spark.sql.*;

import java.util.Arrays;
import java.util.List;
import org.apache.spark.util.Utils;

public class Main7 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("main7")
                .master("local[*]")
                .getOrCreate();

        List<String> list = Arrays.asList("apple", "banana", "orange");

        Dataset<Row> df = sparkSession.createDataset(list, Encoders.STRING()).toDF("Fruit");

        Dataset<Row> df2 = df.withColumn("Fruit", functions.explode(functions.split(df.col("Fruit"), "")));
        Dataset<Row> df3 = df.withColumn("Fruit", functions.explode(functions.split(functions.col("Fruit"), "")));
        df2.show();

        List<String[]> listAr = Arrays.asList(new String[]{"Abebe", "Solomn"}, new String[]{"Henok", "Tylor"});

        Dataset<Row> dfn1 = sparkSession.createDataset(listAr, Encoders.bean(String[].class)).toDF("Name");

        Dataset<Row> df4 = dfn1.withColumn("Name", functions.explode(dfn1.col("Name")));

        df4.show();


        sparkSession.close();
    }
}
