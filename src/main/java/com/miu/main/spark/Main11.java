package com.miu.main.spark;

import com.miu.main.model.Customer;
import com.miu.main.model.Order;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.reflect.ClassTag$;

public class Main11 {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .appName("main11")
                .master("local[*]")
                .getOrCreate();

        final String broadCastValue= " Broad cast variable";

        Broadcast<String> broadcast = spark.sparkContext().broadcast(broadCastValue, ClassTag$.MODULE$.apply(String.class));


        Dataset<Customer> customers = spark.read().parquet("src/main/resources/parquet/customers_data.parquet")
                .withColumnRenamed("customer_id", "customerId")
                .as(Encoders.bean(Customer.class));

        Dataset<Order> orders = spark.read().parquet("src/main/resources/parquet/orders_data.parquet")
                .withColumnRenamed("order_id", "orderId")
                .withColumnRenamed("customer_id", "customerId")
                .withColumnRenamed("order_datetime", "orderDatetime")
                .as(Encoders.bean(Order.class));

//        customers.show();
//
//        orders.show();


        Dataset<Row> joined = customers.join(orders, customers.col("customerId").equalTo(orders.col("customerId")));


        joined.show();

        Dataset<String> map = joined.map(new MapFunction<Row, String>() {
            @Override
            public String call(Row row) throws Exception {
                int customerId = row.getAs("customerId");
                String name = row.getAs("name");

                Customer customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setName(name);
                String value = broadcast.getValue();

                return value+  customer.toString();
            }
        }, Encoders.STRING());

        map.show();


        spark.close();

    }


}
