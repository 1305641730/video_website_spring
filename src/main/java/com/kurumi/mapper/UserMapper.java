package com.kurumi.mapper;

import com.kurumi.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
    User selectByName(User user);
    User selectById(Integer id);
    Integer insertUser(User user);
    User selectOne(User user);
    Integer updateUser(User user);
}
