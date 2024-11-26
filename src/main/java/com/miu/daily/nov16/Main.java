package com.miu.daily.nov16;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user ="myuser";
        String password = "mypassword";
        try(Connection connection = DriverManager.getConnection(url,user,password)) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM sales_data;");

            while (resultSet.next()){
              int id =  resultSet.getInt("id");
                System.out.println(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
