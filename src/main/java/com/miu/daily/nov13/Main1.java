package com.miu.daily.nov13;

import org.apache.spark.sql.*;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("spark")
                .master("local[*]")
                .getOrCreate();
//        46. Perform a `crossJoin` on two DataFrames, display the result, and explain the difference from a regular `join`.
        StructType customerSchema = new StructType()
                .add("customerId", DataTypes.IntegerType)
                .add("name", DataTypes.StringType)
                .add("price", DataTypes.createDecimalType(5, 2));
        List<Row> customerRows = Arrays.asList(
                RowFactory.create(1, "Henok", new BigDecimal("23.5")),
                RowFactory.create(2, "Solomon", new BigDecimal("34.8")),
                RowFactory.create(3, "Henok", new BigDecimal("34.8"))
        );

        StructType productSchema = new StructType()
                .add("productId", DataTypes.IntegerType)
                .add("customerId", DataTypes.IntegerType)
                .add("ProductName", DataTypes.StringType);
        List<Row> productRows = Arrays.asList(
                RowFactory.create(1111, 1, "TV"),
                RowFactory.create(1112, 1, "Radio"),
                RowFactory.create(1113, 2, "Phone")
        );

        Dataset<Row> customerDf = sparkSession.createDataFrame(customerRows, customerSchema);
        Dataset<Row> productDf = sparkSession.createDataFrame(productRows, productSchema);
        customerDf.show();
        productDf.show();
        Dataset<Row> crossJoin = customerDf.crossJoin(productDf);
        crossJoin.show();
        Dataset<Row> join = customerDf.join(productDf, customerDf.col("customerId").equalTo(productDf.col("customerId")), "inner");
        join.show();

//        47. Create a DataFrame and calculate cumulative sum and average on a numeric column.
        Dataset<Row> avg = customerDf.groupBy("name").agg(functions.avg(customerDf.col("price")).alias("avg"));
        avg.show();

        customerDf.createOrReplaceTempView("value_table");

        // Step 3: Perform SQL query for cumulative sum and average
        Dataset<Row> result = sparkSession.sql(
                "SELECT customerId, name, price, " +
                        "SUM(price) OVER (ORDER BY customerId) AS Cumulative_Sum, " +
                        "AVG(price) OVER (ORDER BY customerId) AS Cumulative_Average " +
                        "FROM value_table"
        );
        result.show();

//        48. Use the `coalesce` function to reduce the number of partitions in a DataFrame before writing to disk.


//        49. Load a DataFrame, filter specific rows based on a regular expression, and display the filtered DataFrame.

//        50. Use `withColumn` to add a new column that categorizes values based on another column’s value range.

        sparkSession.stop();
    }
}
