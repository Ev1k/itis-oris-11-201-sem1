package com.derezhenko.net.service.impl;

import com.derezhenko.net.dao.Dao;
import com.derezhenko.net.dao.impl.UserDaoImpl;
import com.derezhenko.net.dto.UserDto;
import com.derezhenko.net.model.User;
import com.derezhenko.net.service.UserService;
import com.derezhenko.net.util.PasswordUtil;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final Dao<User> dao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return dao.getAll().stream().map(
                u -> new UserDto(u.getName(), u.getLastname())
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public void save(User user) {
        user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        dao.save(user);
    }
}
