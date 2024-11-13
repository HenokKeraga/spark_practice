package com.miu.daily.nov11;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;

public class Main1 {

    public static void main(String[] args) {
        // Initialize Spark Session
        SparkSession spark = SparkSession.builder()
                .appName("Date Formatting Example")
                .master("local")
                .getOrCreate();

        // Sample data with a date column
        List<Row> data = Arrays.asList(
                RowFactory.create("2024-02-15"),
                RowFactory.create("2024-04-20"),
                RowFactory.create("2024-07-10"),
                RowFactory.create("2024-10-05")
        );

        // Define schema for the DataFrame
        StructType schema = new StructType()
                .add("invEligDT", DataTypes.StringType, false);

        // Creating DataFrame from the list of rows
        Dataset<Row> dataFrame = spark.createDataFrame(data, schema);
        dataFrame.show();
        //Convert a string date column to DateType in format "yyyy-MM-dd".
        Dataset<Row> one = dataFrame.withColumn("oneColumn", functions.to_date(col("invEligDT"), "yyyy-MM-dd"));
        one.show();
        //Format a date column to "dd-MM-yyyy"
        Dataset<Row> two = one.withColumn("twoColumn", functions.date_format(one.col("oneColumn"), "dd-MM-yyyy"));
        two.show();

        //Add columns for the current date and timestamp.
        Dataset<Row> three = two.withColumn("threeColumn", functions.current_timestamp())
                .withColumn("fourColumn", functions.current_date());
        three.show();
        //Extract the year from a date column.
        Dataset<Row> five = dataFrame.withColumn("fiveColumn", functions.year(dataFrame.col("invEligDT")));
        five.show();

        //Extract the month and day of the month from a date column.
        Dataset<Row> six = dataFrame.withColumn("sixColumn", functions.dayofmonth(dataFrame.col("invEligDT")));
        six.show();
        // Stop the Spark session
        spark.stop();
    }


}
/**
 * Dataset<Row> df = dataFrame
 * <p>
 * .withColumn("invEligDT", functions.to_date(col("invEligDT"), "yyyy-MM-dd"));
 * <p>
 * df.show();
 * // Truncate to the start of the quarter and format to "yyyy-MM-dd"
 * Dataset<Row> formattedDF = df.select(
 * functions.date_format(
 * functions.date_trunc("QUARTER", df.col("invEligDT")), "yyyy-MM-dd"
 * ).alias("StartOfQuarter")
 * );
 * <p>
 * // Show the resulting DataFrame
 * formattedDF.show();
 */