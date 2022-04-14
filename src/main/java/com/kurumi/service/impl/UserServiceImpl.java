package com.kurumi.service.impl;

import com.kurumi.domain.User;
import com.kurumi.mapper.UserMapper;
import com.kurumi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public boolean isExist(User user) {
        User userExist = userMapper.selectByName(user);
        if(userExist != null) return true;
        return false;
    }

    @Override
    public boolean register(User user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public User login(User user) {
        User userLogin = userMapper.selectOne(user);
        return userLogin;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }
}
