package com.miu.main;

import com.miu.model.Person;
import org.apache.spark.sql.*;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> studentCsvs = spark.read().option("header", true).csv("src/main/resources/csv/student_data.csv");
        Dataset<Row> subjectCsvs = spark.read().option("header", true).csv("src/main/resources/csv/subject_data.csv");
        studentCsvs.write().mode(SaveMode.Overwrite).parquet("src/main/resources/parquet/student_data.parquet");
        subjectCsvs.write().mode(SaveMode.Overwrite).parquet("src/main/resources/parquet/subject_data.parquet");


        Dataset<Row> students = spark.read().parquet("src/main/resources/parquet/student_data.parquet");
        Dataset<Row> subjects = spark.read().option("header", true).parquet("src/main/resources/parquet/subject_data.parquet");

        Dataset<Row> join = students
               // .join(subjects, students.col("subject_id").equalTo(subjects.col("subject_id")), "right")
                .join(subjects, "subject_id", "right")
                .filter(subjects.col("subject_id").isInCollection(new ArrayList<String>() {{
                    add("101");
                    add("102");
                }}))
                .select(students.col("student_id"), students.col("student_name"), subjects.col("subject_name"));

        Dataset<Tuple2<Row, Row>> tuple2Dataset = students.joinWith(subjects, students.col("subject_id").equalTo(subjects.col("subject_id")));
        tuple2Dataset.show();
        Dataset<Row> studentId = students.select(students.col("student_id"));
        Dataset<Row> studentId1 = students.filter(students.col("student_id").equalTo(2));


        studentId1.show();

        studentId.show();


        join.show();

        students.show();
        subjects.show();

        spark.close();
    }
}
