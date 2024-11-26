package com.miu.main.daily.nov11;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import static org.apache.spark.sql.functions.col;

public class Main {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession
                .read()
                .option("header", true)
                .csv("src/main/resources/nov11/sample_dataframe.csv");

        dataset.show();
//        Load a DataFrame from a CSV file, select specific columns, and display the schema.
        Dataset<Row> select = dataset.select("ID", "Name", "Salary");
        select.show();
        Dataset<Row> selectExpr = dataset.selectExpr("ID", "Name", "COALESCE(Salary, 'No salary')", "NULLIF(Salary, 0)", "ISNULL(Salary)");
        selectExpr.show();
        Dataset<Row> col = dataset.select(dataset.col("Id"), dataset.col("Name"), dataset.col("Salary"));
        col.show();

//                Filter a DataFrame to include only rows where a column contains a specific substring.
        Dataset<Row> subString = dataset.filter(dataset.col("city").contains("New"));
        subString.show();
//                Create a DataFrame and apply the distinct method to remove duplicate rows.
        Dataset<Row> distinct = dataset.dropDuplicates("City"); // .distinct
        distinct.show();
//                Sort a DataFrame by a specific column in ascending and descending order, then display the
//        sorted DataFrame.
//        Drop columns with null values from a DataFrame and display the resulting DataFrame.
//                Load a DataFrame and use the withColumnRenamed function to rename a specific column.
//        Create a DataFrame and apply multiple filters using filter and where.
//                Use the selectExpr method to perform SQL -like transformations on DataFrame columns.
//        Create a DataFrame with duplicate values and remove duplicates using dropDuplicates.
//                Use join to merge two DataFrames on a common key, and display the joined DataFrame

        sparkSession.stop();
    }
}
