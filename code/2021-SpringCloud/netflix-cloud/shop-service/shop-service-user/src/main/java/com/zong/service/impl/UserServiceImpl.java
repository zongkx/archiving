package com.zong.service.impl;

import com.zong.service.UserService;
import com.zong.user.pojo.User;
import com.zong.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    public List<User> findAll() {
        return mapper.selectAll();
    }

    @Override
    public User findById(String name) {
        return mapper.selectByPrimaryKey(name);
    }

    @Override
    public User add(User user) {
        mapper.insert(user);
        return user;
    }


}
