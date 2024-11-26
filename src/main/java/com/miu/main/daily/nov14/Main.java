package com.miu.main.daily.nov14;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("main")
                .master("local[*]")
                .getOrCreate();
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user ="myuser";
        String password = "mypassword";
        String partitionColumn = "id";  // Specify the column used for partitioning
        int lowerBound = 1;             // Specify the lower bound for partitioning
        int upperBound = 10;        // Specify the upper bound for partitioning
        int numPartitions = 10;
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("driver", "org.postgresql.Driver");

//        Dataset<Row> dataset = sparkSession
//                .read()
//                .format("jdbc")
//                .option("url", url)
//                .option("dbtable", "sales_data")
//                .option("user", user)
//                .option("password", password)
//                .option("driver", "org.postgresql.Driver")
//                .load();
        Dataset<Row> jdbcDF = sparkSession.read()
                .jdbc(url, "sales_data", partitionColumn, lowerBound, upperBound, numPartitions, connectionProps);
        // Step 2: Database Connection Details


        // Step 3: Drop Table Query
        String dropTableQuery = "DROP TABLE IF EXISTS sales_data";
        String grantPrivileges = "GRANT ALL PRIVILEGES ON sales_data TO PUBLIC";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(grantPrivileges);
            System.out.println("Table dropped successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        jdbcDF.show();

        sparkSession.stop();

    }
}
