package com.derezhenko.net.dao.impl;

import com.derezhenko.net.dao.Dao;
import com.derezhenko.net.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements Dao<User> {

    private final Connection connection = null;

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, lastname, email, login, password) values (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            PreparedStatement preparedStatement1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
