package com.miu.main.daily.nov12;

import com.miu.main.model.practice.Product;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();

//        Given a DataFrame of people with columns "name" and "age," filter the rows where age is above 18 and sort by age in descending order.
        StructType schema = new StructType()
                .add("name", DataTypes.StringType)
                .add("age", DataTypes.IntegerType);

        List<Row> people = Arrays.asList(
                RowFactory.create("Henok", 23),
                RowFactory.create("Woin", 10),
                RowFactory.create("Ermias", 50),
                RowFactory.create("Solomon", 30),
                RowFactory.create("Maza", 1),
                RowFactory.create("Tigist", 15)
        );

        Dataset<Row> dataframe = spark.createDataFrame(people, schema);
        dataframe.show();
        Dataset<Row> ordered = dataframe.filter(dataframe.col("age").gt(18)).orderBy(dataframe.col("age").desc());
        ordered.show();

//                Create a DataFrame with a timestamp column, extract the month and year into separate columns, and display the result.

//                Use explode on an array column in a DataFrame to flatten it into individual rows.
        StructType schema2 = new StructType()
                .add("Name", DataTypes.StringType, false)
                .add("productList", DataTypes.createArrayType(DataTypes.StringType), false);
        List<Row> list = Arrays.asList(
                RowFactory.create("henok", Arrays.asList("TV", "Radio", "Computer")),
                RowFactory.create("Solomon", Arrays.asList("Desk", "Chair", "Bottle"))
        );

        Dataset<Row> dataFrame2 = spark.createDataFrame(list, schema2);
        dataFrame2.show();
        Dataset<Row> explode = dataFrame2
                .withColumn("product", functions.explode(dataFrame2.col("productList")))
                .drop(dataFrame2.col("productList"));
        explode.show();


        Dataset<Product> flatMap = dataFrame2.flatMap(new FlatMapFunction<Row, Product>() {

            @Override
            public Iterator<Product> call(Row row) throws Exception {
                String name = row.getString(0);
                List<String> list1 = row.getList(1);

                return list1.stream()
                        .map(pr -> {
                            Product person = new Product();
                            person.setName(name);
                            person.setProduct(pr);

                            return person;
                        })
                        .collect(Collectors.toList()).iterator();
            }
        }, Encoders.bean(Product.class));
        flatMap.show();


        spark.stop();
    }

}
