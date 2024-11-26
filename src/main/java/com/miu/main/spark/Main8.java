package com.miu.main.spark;

import com.miu.main.repository.GCSRepository;
import com.miu.main.repository.PostgresRepository;
import org.apache.spark.sql.*;

public class Main8 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main8")
                .master("local[*]")
                .getOrCreate();

        PostgresRepository postgresRepository = new PostgresRepository(spark);
        GCSRepository gcsRepository = new GCSRepository(spark);
        //customers
        Dataset<Row> customersDataset = postgresRepository.read("customers");
        gcsRepository.write(customersDataset, "src/main/resources/parquet/customers_data.parquet");
        //orders
        Dataset<Row> ordersDataset = postgresRepository.read("orders");
        gcsRepository.write(ordersDataset, "src/main/resources/parquet/orders_data.parquet");


        //********** program start here


        Dataset<Row> customers = gcsRepository.read("src/main/resources/parquet/customers_data.parquet");
        Dataset<Row> orders = gcsRepository.read("src/main/resources/parquet/orders_data.parquet");

        customers.show();
        orders.show();

        Dataset<Row> orderTime = orders.select(orders.col("order_id"),
                functions.date_format(orders.col("order_datetime"), "DD").alias("order_day"),
                functions.date_format(orders.col("order_datetime"), "MMMM").alias("order_month")
        );
        orderTime.show();

        Dataset<Row> orderMonth = orderTime.groupBy(orderTime.col("order_month"), orderTime.col("order_id")).count();

        orderMonth.show();


//        customers.write().mode(SaveMode.Overwrite).csv("src/main/resources/csv/customer-data.csv");



        spark.close();

    }
}
