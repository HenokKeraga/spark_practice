package com.miu.main.excercise;

import com.miu.main.model.practice.CustomerOrderDTO;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class Practice6 {
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
        Dataset<CustomerOrderDTO> customerOrderDTODataset = spark.read().option("header", "true")
                .schema(schema)
                .csv("src/main/resources/csv/customer_order.csv").as(Encoders.bean(CustomerOrderDTO.class));

        customerOrderDTODataset.show();

        Dataset<Row> phone = customerOrderDTODataset
//                .groupBy("phone")   //Dataset API in Apache Spark and called grouping function or grouping operation
                .agg(
                        functions.max("phone") //aggregation function
                );

        phone.show();
        spark.close();
    }
}
