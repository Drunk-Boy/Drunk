package com.xu.service;

import com.xu.entities.User;
import com.xu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    //登陆验证
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    //注册
    public void userInsert(User user){
        userMapper.userInsert(user);
    }
}
