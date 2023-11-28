package com.derezhenko.net;

import com.derezhenko.net.model.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        System.out.println("hello world");

        Class.forName("org.postgresql.Driver");
        java.sql.Connection connection =  DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "rfrfirf12");

        java.sql.Statement statement = connection.createStatement();
        String sql = "SELECT * from users";
        ResultSet resultSet = statement.executeQuery(sql);
        int size = 0;
        List<User> users = new ArrayList<>();

        if (resultSet != null) {
            while (resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("lastname"),
                                resultSet.getString("email"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
                size++;
            }
//            resultSet.beforeFirst();
//            resultSet.last();
            System.out.println(resultSet.getRow());
        }

    }
}
