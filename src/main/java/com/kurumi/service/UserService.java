package com.kurumi.service;

import com.kurumi.domain.User;

import java.util.List;

public interface UserService {

    List<User> selectAll();
    User selectById(Integer id);
    boolean isExist(User user);
    boolean register(User user);
    User login(User user);
    boolean updateUser(User user);
}
