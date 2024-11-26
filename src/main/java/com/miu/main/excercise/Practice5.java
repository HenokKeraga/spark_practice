package com.miu.main.excercise;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;

import static org.apache.spark.sql.functions.*;

public class Practice5 {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .appName("practice1")
                .master("local[*]")
                .getOrCreate();

        StructType schema = new StructType()
                .add("customerId", DataTypes.LongType)
                .add("name", DataTypes.StringType)
                .add("email", DataTypes.StringType)
                .add("phone", DataTypes.StringType)
                .add("address", DataTypes.StringType)
                .add("orderId", DataTypes.LongType)
                .add("productName", DataTypes.StringType)
                .add("quantity", DataTypes.LongType)
                .add("price", DataTypes.createDecimalType(10, 2))
                .add("orderDate", DataTypes.DateType);


        Dataset<Row> customerOrderDTODataset = spark.read().option("header", "true")
                .schema(schema)
                .csv("src/main/resources/csv/customer_order.csv")

//                .withColumn("quantity", col("quantity").cast(DataTypes.LongType))
//                .withColumn("price", col("price").cast(DataTypes.createDecimalType()))
//                .withColumn("orderDate", col("orderDate").cast(DataTypes.DateType))
//                .withColumn("orderId", col("orderId").cast(DataTypes.LongType))
//                .withColumn("customerId", col("customerId").cast(DataTypes.LongType))
               ;
        customerOrderDTODataset.show();

        customerOrderDTODataset
                .groupBy("customerId", "name")
               // .pivot("orderId", Arrays.asList("Male","F"))
                .agg(
                        sum("price").alias("cost"),
                        avg("price").alias("averagePrice"),
                        max("price").alias("maxPrice")
                ).show();


        customerOrderDTODataset
                .groupBy("customerId", "name")

                .agg(collect_list("price").alias("price-list")).show();


        customerOrderDTODataset
                .groupBy("customerId", "name")
                .agg(collect_list(
                        when(
                                customerOrderDTODataset.col("quantity").isNotNull(), struct("quantity", "price")
                        )
                ).alias("cost"))
                .show();


        spark.stop();

    }
}
