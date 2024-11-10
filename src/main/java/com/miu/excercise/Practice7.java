package com.miu.excercise;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.col;

public class Practice7 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("JSON to DataFrame Example")
                .master("local[*]")
                .getOrCreate();
        //Create a DataFrame from a JSON file and display the first 5 rows.

        StructType schema = new StructType()
                .add("id", DataTypes.LongType)
                .add("name", DataTypes.StringType)
                .add("age", DataTypes.IntegerType)
                .add("department", DataTypes.StringType)
                .add("salary", DataTypes.LongType)
                .add("address", new StructType()
                        .add("city", DataTypes.StringType)
                        .add("state", DataTypes.StringType))
                .add("skills", DataTypes.createArrayType(DataTypes.StringType));

        Dataset<Row> dataset = sparkSession
                .read()
                .schema(schema)
                .option("multiline", true)
                .json("src/main/resources/json/employee.json");

        dataset.printSchema();

        dataset.show(5, false);

        //Load a CSV file as a DataFrame, filter the rows where a specific column is greater than a threshold, and save the result as a new CSV file.

        Dataset<Row> csv = sparkSession
                .read()
                .option("header", true)
                .csv("src/main/resources/csv/student_data.csv");
        Dataset<Row> score = csv.filter(col("score").cast(DataTypes.IntegerType).gt(50));

        csv.show();

        score.show();

        score.write().mode(SaveMode.Overwrite).option("header", true).csv("src/main/resources/csv/student_pass_data");

        //Write a DataFrame to Parquet format, read it back, and verify the schema.
        score.write().mode(SaveMode.Overwrite).parquet("src/main/resources/parquet/student_pass_data");

        Dataset<Row> parquet = sparkSession.read().parquet("src/main/resources/parquet/student_pass_data");

        parquet.printSchema();

        sparkSession.stop();
    }
}
