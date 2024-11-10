package com.miu.excercise;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Practice3 {

    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("test")
                .master("local[*]")
                .getOrCreate();

        //Load a text file into an RDD, count the number of words in the file, and filter out words shorter than 4 characters.
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession.sparkContext());

        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile("src/main/resources/text/words.txt");

//        stringJavaRDD.foreachPartition(partition -> {
//            partition.forEachRemaining(word -> {
//                String[] string = word.split(" ");
//                for (int i = 0; i < string.length; i++) {
//                    System.out.println(string[i]);
//                }
//
//            });
//        });


        JavaRDD<String> stringJavaRDD1 = stringJavaRDD.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });

        long count = stringJavaRDD1.filter(word -> word.length() >= 4).count();

        System.out.println("count "+ count);


//        stringJavaRDD.collect().forEach(word -> System.out.println(word));
        // stringJavaRDD.foreach(word -> System.out.println(word));
//        stringJavaRDD.foreach(System.out::println);

        Dataset<String> datasetText = sparkSession.read().text("src/main/resources/text/words.txt").as(Encoders.bean(String.class));

        Dataset<String> stringDataset = datasetText.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        }, Encoders.STRING());

        stringDataset.show();

        Dataset<Row> dataset = datasetText
                .withColumn("words", functions.split(datasetText.col("value"), " "))
                .withColumn("word",functions.explode(functions.col("words")));


        dataset
                .show();



        sparkSession.stop();
    }
}
