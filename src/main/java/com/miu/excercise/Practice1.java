package com.miu.excercise;

import com.miu.model.practice.CustomerOrderDTO;
import com.miu.model.practice.CustomerTwo;
import org.apache.spark.sql.*;

import static org.apache.spark.sql.functions.*;

public class Practice1 {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder()
                .appName("practice1")
                .master("local[*]")
                .getOrCreate();
        //


        Dataset<CustomerOrderDTO> customerOrderDTODataset = spark.read().option("header", "true")
                .csv("src/main/resources/csv/customer_order.csv")
                .withColumn("orderId", col("orderId").cast("long"))
                .as(Encoders.bean(CustomerOrderDTO.class));
        Dataset<CustomerTwo> agg = customerOrderDTODataset.groupBy("customerId", "name", "email","phone", "address")
                .agg(
                        collect_list(
                                when(
                                        customerOrderDTODataset.col("orderId").isNotNull(),
                                        struct("orderId", "productName", "quantity", "price", "orderDate")
                                )

                        )
                                .as("orders")
                ).as(Encoders.bean(CustomerTwo.class));


       agg.show();

//        Dataset<CustomerTwo> agg = customerOrderDTODataset.groupBy("customerId", "name", "email", "phone", "address")
//                .agg(
//                        when(
////                                collect_list(
////                                        struct("orderId", "productName", "quantity", "price", "orderDate")
////                                ).isNotNull()
//                                col("orderId").isNaN()
//                                ,
//                                collect_list(
//                                        struct("orderId", "productName", "quantity", "price", "orderDate")
//                                )
//                        ).otherwise(
//                                array()
//                        ).as("orders")
//                )
//                .as(Encoders.bean(CustomerTwo.class));
//        agg.show();

        Dataset<CustomerTwo> agg2 = customerOrderDTODataset.groupBy("customerId", "name", "email", "phone", "address")
                .agg(
                        collect_list(
                                struct("orderId", "productName", "quantity", "price", "orderDate")
                        ).as("orders")
                )
                .as(Encoders.bean(CustomerTwo.class));


        agg2.show();

        Dataset<CustomerTwo> agg3 = customerOrderDTODataset.groupBy("customerId", "name", "email", "phone", "address")
                .agg(
                        collect_list(
                                when(customerOrderDTODataset.col("orderId").isNotNull(), struct("orderId", "productName", "quantity", "price", "orderDate"))
                        ).as("orders")
                )
                .as(Encoders.bean(CustomerTwo.class));


        agg3.show();

        Dataset<CustomerTwo> agg4 = customerOrderDTODataset.groupBy("customerId", "name", "email", "phone", "address")
                .agg(
                        coalesce(
                                collect_list(
                                        when(
                                                customerOrderDTODataset.col("orderId").isNotNull(),
                                                struct("orderId", "productName", "quantity", "price", "orderDate")
                                        )
                                )

                        ).as("orders")

                )
                .as(Encoders.bean(CustomerTwo.class));


        agg4.show();

        spark.stop();

    }
}
