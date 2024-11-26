package com.miu.main.daily.nov13;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();

        //  Load a DataFrame from a CSV, handle missing values by filling them with default values, and display the cleaned DataFrame.

        Dataset<Row> csv = spark.read().option("header", true).csv("src/main/resources/csv/customer_order.csv");
        csv.show();
        HashMap<String, String> replacementMap = new HashMap<>();
        replacementMap.put("N/A", "7");
        Dataset<Row> fill = csv
                .na().fill("UNKNOWN", new String[]{"productName"})
                .na().fill("0", new String[]{"orderId", "price"})
                .na().replace("quantity", replacementMap);
        // .na().drop();

        fill.show();

        spark.stop();

    }
}
