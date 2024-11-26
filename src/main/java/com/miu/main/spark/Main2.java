package com.miu.main.spark;

import com.miu.main.model.Person;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Arrays;

import static org.apache.spark.sql.functions.lit;
import static org.apache.spark.sql.functions.when;

public class Main2 {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();


        Dataset<Person> personDataset = spark.createDataset(
                Arrays.asList(
                        new Person(1, "John", 30),
                        new Person(2, "Jane", 25)
                ),
                Encoders.bean(Person.class)
        );

        Dataset<Row> df = personDataset.toDF("ID", "First-Name", "Age");
        df.show();

        Dataset<Row> dataset = personDataset.withColumn("name", when(personDataset.col("id").equalTo(1), "Abebe")
                .otherwise("Solomon")).withColumn("mother-name",lit("karma"));
        dataset.show();

        Dataset<Row> ids = personDataset.select("id")
                .filter((FilterFunction<Row>) row -> row.getInt(0) == 1);

        ids.show();

        Dataset<Integer> integerDataset = spark.createDataset(new ArrayList<Integer>() {{
            add(1);
            add(2);
        }}, Encoders.INT());
        integerDataset.show();


//        Integer reduce = integerDataset.reduce((Function2<Integer, Integer, Integer>) Integer::sum);
//
//        System.out.println("reduce ::" +reduce);
//
//        Dataset<String> stringDataset1 = integerDataset.as(Encoders.STRING());
//        stringDataset1.show();


        // Convert the Dataset to a DataFrame
        Dataset<Row> personDF = personDataset.toDF();

        // Display the DataFrame
        personDF.show();
    }
}
