package com.miu.main.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class PostgresRepository {
    private final SparkSession sparkSession;

    public PostgresRepository(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public Dataset<Row> read(String dbtable) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user ="myuser";
        String password = "mypassword";

        return sparkSession
                .read()
                .format("jdbc")
                .option("url", url)
                .option("dbtable", dbtable)
                .option("driver", "org.postgresql.Driver")
                .option("user", user)
                .option("password", password)
                .load();
    }

    public void write(Dataset<Row> dataset,String dbtable){
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user ="myuser";
        String password = "mypassword";
        dataset.write()
                .format("jdbc")
                .option("url", url)
                .option("dbtable", dbtable)
                .option("driver", "org.postgresql.Driver")
                .option("user", user)
                .option("password", password)
                .mode(SaveMode.Append)
                .save();

    }
}
