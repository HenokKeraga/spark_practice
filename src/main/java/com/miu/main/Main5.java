package com.miu.main;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.rdd.PairRDDFunctions;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import scala.Tuple3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main5 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main5")
                .master("local[*]")
                .getOrCreate();

        JavaSparkContext javaSparkContext = new JavaSparkContext(spark.sparkContext());

        Tuple3<String, String, String> tuple3 = new Tuple3<>("lamb", "goose", "partridge");

        List<Tuple2<String, Integer>> data = Arrays.asList(
                new Tuple2<>("apple", 1),
                new Tuple2<>("banana", 2),
                new Tuple2<>("cherry", 3)
        );

        Dataset<Tuple2<String, Integer>> dataset = spark.createDataset(data, Encoders.tuple(Encoders.STRING(), Encoders.INT()));

        dataset.show();
        Dataset<Row> dataset1 = dataset.toDF("Fruit", "quantity");
        dataset1.show();

        JavaPairRDD<String, Integer> pairRDD = javaSparkContext.parallelizePairs(data);
        pairRDD.foreach(tu-> System.out.println( tu._1));


        List<String> list = Arrays.asList("apple", "orange");

        JavaRDD<String> fruitsJRDD = javaSparkContext.parallelize(list);

        List<Integer> collect = fruitsJRDD.map(s -> 1).collect();

        System.out.println(collect);

        Dataset<String> dataset2 = spark.createDataset(list, Encoders.STRING());

        Dataset<Integer> map = dataset2.map((MapFunction<String, Integer>) s -> 2, Encoders.INT());
        List<Integer> integers = map.collectAsList();

        System.out.println(integers);


        spark.close();

    }
}
