package com.miu.repository;

import com.miu.main.repository.GCSRepository;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GCSRepositoryTest {
    private GCSRepository gcsRepository;
    private SparkSession spark;

    @BeforeEach
    void setUp() {

        spark = SparkSession.builder()
                .appName("test")
                .master("local[*]")
                .getOrCreate();
        gcsRepository = new GCSRepository(spark);
    }


    @Test
    void read() {

        Dataset<Row> dataset = gcsRepository.read("src/main/resources/parquet/customers_data.parquet");
        assertEquals(4, dataset.count());
    }

    @AfterEach
    void tearDown() {
        spark.close();
    }
}