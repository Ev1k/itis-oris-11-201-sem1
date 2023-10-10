package com.derezhenko.net.service;

import com.derezhenko.net.dto.UserDto;
import com.derezhenko.net.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(int id);
    void save(User user);

}
