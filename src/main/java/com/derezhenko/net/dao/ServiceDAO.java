package com.derezhenko.net.dao;

import com.derezhenko.net.model.Service;
import com.derezhenko.net.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements Dao<Service> {
    private final Connection connection = DatabaseConnectionUtil.getConnection();

    @Override
    public Service get(int id) {
        return null;
    }

    @Override
    public List<Service> getAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * from services";
            ResultSet resultSet = statement.executeQuery(sql);
            List<Service> services = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    services.add(
                            new Service(
                                    resultSet.getInt("id"),
                                    resultSet.getString("name")
                            )
                    );
                    System.out.println();
                }
            }
            return services;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Service service) {
        String sql = "insert into service (name) values (?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Nails");
            preparedStatement.executeUpdate();
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
