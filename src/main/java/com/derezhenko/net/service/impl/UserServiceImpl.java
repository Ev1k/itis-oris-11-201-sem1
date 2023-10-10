package com.derezhenko.net.service.impl;

import com.derezhenko.net.dto.UserDto;
import com.derezhenko.net.model.User;
import com.derezhenko.net.service.UserService;
import com.derezhenko.net.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    @Override
    public UserDto get(int id) {
        return null;
    }

    @Override
    public void save(User user) {
//        user.setPassword(PasswordUtil.encrypt());
        return;
    }
}
