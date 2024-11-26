package com.miu.main.spark;


import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.runtime.BoxedUnit;


import java.util.Arrays;

public class Main4 {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main3")
                .master("local[1]")
                .getOrCreate();



        Dataset<Row> dataset = spark.createDataset(Arrays.asList(4, 16, 9), Encoders.INT()).toDF();
        dataset.show();

//        Dataset<Double> map = dataset.map((Function1<Row, Double>) row -> Math.sqrt(row.getInt(0)), Encoders.DOUBLE());
//        map.show();
//        Dataset<Double> map1 = dataset.map((Function1<Integer, Double>) row -> Math.sqrt(row), Encoders.DOUBLE());
//        map1.show();

        Dataset<Double> dataset1 = dataset.map((MapFunction<Row, Double>) row -> Math.sqrt(row.getInt(0)), Encoders.DOUBLE());
        dataset1.show();

        dataset1.foreach((ForeachFunction<Double>) aDouble -> System.out.println("value ::" + aDouble));

        System.out.println("count + "+dataset1.count());

        spark.close();
    }
}
