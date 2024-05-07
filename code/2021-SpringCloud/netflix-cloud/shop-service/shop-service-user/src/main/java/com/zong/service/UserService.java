package com.zong.service;

import com.zong.user.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String name);

    User add(User user);
}
