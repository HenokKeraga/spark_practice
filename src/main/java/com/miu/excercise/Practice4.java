package com.miu.excercise;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice4 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("test")
                .master("local[*]")
                .getOrCreate();
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession.sparkContext());
        // Given two RDDs, join them on a common key and display the results.
        List<Tuple2<Integer, String>> customerTuple = Arrays.asList(new Tuple2<>(1, "Henok"), new Tuple2<>(2, "Sol"));

        List<Tuple2<Integer, String>> orderTuple = Arrays.asList(new Tuple2<>(1, "TV"));

        JavaPairRDD<Integer, String> customers = javaSparkContext.parallelizePairs(customerTuple);
        JavaPairRDD<Integer, String> orders = javaSparkContext.parallelizePairs(orderTuple);

        JavaPairRDD<Integer, Tuple2<String, String>> join = customers.join(orders);
        JavaPairRDD<Integer, Tuple2<String, Optional<Tuple2<String, String>>>> integerTuple2JavaPairRDD = customers.leftOuterJoin(join);

        join.foreachPartition(partition -> partition.forEachRemaining(data-> System.out.println(data)));
        integerTuple2JavaPairRDD.foreachPartition(partition -> partition.forEachRemaining(pair -> System.out.println(pair)));


        sparkSession.close();
    }
}
