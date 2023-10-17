package com.derezhenko.net.dao;

import com.derezhenko.net.model.Master;
import com.derezhenko.net.util.DatabaseConnectionUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO implements Dao<Master> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Master get(int id) {
        return null;
    }

    @Override
    public List<Master> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from masters";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Master> masters = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    masters.add(
                            new Master(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name")
                            )
                    );
                    System.out.println();
                }
            }
            return masters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Master master) {
        String sql = "insert into masters (name) values (?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Алина");
            preparedStatement.executeUpdate();
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
