package com.miu.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class GCSRepository {
    private final SparkSession sparkSession;

    public GCSRepository(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public Dataset<Row> read(String path){
       return sparkSession.read().parquet(path);
    }

    public void write(Dataset<Row> dataset, String path){
        dataset.coalesce(1).write().mode(SaveMode.Overwrite).parquet(path);
    }

}
