package com.miu.main;

import com.miu.repository.PostgresRepository;
import com.sun.prism.PixelFormat;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

public class Main9 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main9")
                .master("local[*]")
                .getOrCreate();

        PostgresRepository postgresRepository = new PostgresRepository(spark);
        ArrayList<StructField> structFields = new ArrayList<>();
        structFields.add(DataTypes.createStructField("cumstomer_id",DataTypes.StringType,false));
        structFields.add(DataTypes.createStructField("name",DataTypes.StringType,false));


        StructType structType1 = DataTypes.createStructType(structFields);

//        StructType structType = new StructType()
//                .add("customer_id", DataTypes.IntegerType)
//                .add("name", DataTypes.StringType);
        Dataset<Row> csvDataset = spark.read().option("header", true).schema(structType1).csv("src/main/resources/csv/customer-data.csv");

        postgresRepository.write(csvDataset, "customers");


        spark.close();
    }
}
