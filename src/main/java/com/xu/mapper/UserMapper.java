package com.xu.mapper;

import com.xu.entities.User;

import java.util.List;

public interface UserMapper {
    //查找所有user
    public List<User> getAllUser();

    //注册
    public void userInsert(User user);
}
