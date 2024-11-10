package com.miu.excercise;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice2 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("practice1")
                .master("local[*]")
                .getOrCreate();

        JavaSparkContext javaSparkContext = new JavaSparkContext(spark.sparkContext());
        //  Create an RDD from a list of integers, filter out the even numbers, and find their sum.
        List<Integer> listInt = Arrays.asList(1, 2, 3, 4);
        JavaRDD<Integer> javaRDDListInt = javaSparkContext.parallelize(listInt);

        Integer sum = javaRDDListInt.filter(v -> v % 2 == 0).reduce(Integer::sum);

        System.out.println("sum ::" + sum);


        spark.stop();
    }
}
