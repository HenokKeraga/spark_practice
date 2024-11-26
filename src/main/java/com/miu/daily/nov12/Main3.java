package com.miu.daily.nov12;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder().appName("main").master("local[*]").getOrCreate();

        //Calculate the days between two date columns, start_date and end_date.

        StructType schema = new StructType()
                .add("TermDate", DataTypes.DateType)
                .add("EffDate", DataTypes.DateType);

        List<Row> rows = Arrays.asList(
                RowFactory.create(LocalDate.now(), LocalDate.of(2020, 2, 12))
        );
        Dataset<Row> dataFrame = sparkSession.createDataFrame(rows, schema);

        dataFrame.show();
        Dataset<Row> dataset = dataFrame.withColumn("diff",  functions.datediff(dataFrame.col("TermDate"), dataFrame.col("EffDate")));
        Dataset<Row> dataset1 = dataFrame.withColumn("diff",  functions.date_add(dataFrame.col("TermDate"), 1));

        dataset.show();
        dataset1.show();

        sparkSession.stop();
    }
}
