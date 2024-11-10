package com.miu.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresRepositoryTest {
    PostgresRepository postgresRepository;
    SparkSession sparkSession;

    @BeforeEach
    void setUp() {
        sparkSession = SparkSession.builder()
                .appName("test")
                .master("local[*]")
                .getOrCreate();
        postgresRepository = new PostgresRepository(sparkSession);

    }

    @Test
    void read() {
        Dataset<Row> dataset = postgresRepository.read("");

        assertEquals(4,dataset.count());
    }

    @AfterEach
    void tearDown() {
        sparkSession.close();
    }
}