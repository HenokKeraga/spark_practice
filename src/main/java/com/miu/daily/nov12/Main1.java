package com.miu.daily.nov12;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();

//        Create a DataFrame and perform a pivot operation, grouping by one column and pivoting by another, to calculate the average of a numeric column.
        StructType schema = new StructType()
                .add("name", DataTypes.StringType)
                .add("grade", DataTypes.StringType)
                .add("mark", DataTypes.IntegerType);

        List<Row> rows = Arrays.asList(
                RowFactory.create("Henok","A",99),
                RowFactory.create("Solomon","B",90),
                RowFactory.create("Solomon","A",95),
                RowFactory.create("Abebe","C",80),
                RowFactory.create("Abebe","A",95),
                RowFactory.create("Henok","A",98)

        ) ;
        Dataset<Row> dataFrame = sparkSession.createDataFrame(rows, schema);
        dataFrame.show();
        Dataset<Row> agg = dataFrame.groupBy(dataFrame.col("name")).pivot(dataFrame.col("grade")).agg(functions.avg(dataFrame.col("mark")));
        agg.show();




        sparkSession.stop();
    }
}
