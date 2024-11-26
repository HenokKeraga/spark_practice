package com.miu.daily.nov16;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        //Create a DataFrame with hierarchical data, use explode to flatten the hierarchy, and display the result.

        //Load a DataFrame with JSON data, parse nested fields, and create a new DataFrame with flattened structure.

        SparkSession sparkSession = SparkSession.builder().appName("name").master("local[*]").getOrCreate();

//        StructType schema = new StructType()
//                .add("id", DataTypes.IntegerType)
//                .add("customer", DataTypes.StringType.json());

        List<Person> rows = Arrays.asList(
                new Person(1, "henok", Arrays.asList("Swim", "Run", "read")
                ));

        Dataset<Row> dataFrame = sparkSession.createDataFrame(rows, Person.class);
        Dataset<Person> dataset = sparkSession.createDataset(rows, Encoders.bean(Person.class));

        dataFrame.show();
        dataset.show();

        Dataset<Row> dataset1 = dataFrame.withColumn("hobby", functions.explode(dataFrame.col("hobbies")));


        dataset1.show();

        sparkSession.stop();

    }

    public static class Person {
        private int index;
        private String name;
        private List<String> hobbies;

        public Person() {
        }

        public Person(int index, String name, List<String> hobbies) {
            this.index = index;
            this.name = name;
            this.hobbies = hobbies;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getHobbies() {
            return hobbies;
        }

        public void setHobbies(List<String> hobbies) {
            this.hobbies = hobbies;
        }
    }
}
