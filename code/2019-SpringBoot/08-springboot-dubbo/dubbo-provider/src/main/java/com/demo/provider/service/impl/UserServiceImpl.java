package com.demo.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.dao.UserMapper;
import com.dubbo.model.User;
import com.dubbo.service.UserService;

@Component

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserServiceImpl implements UserService{
	
	@Autowired 
	private UserMapper userMapper;
	
	
	@Override
	public String sayHi(String name) {
		return "hello dubbo";
	}

	@Override
	public User selectByAccountAndPassword(String uAccount, String uPassword) {
		return userMapper.selectByAccountAndPassword(uAccount, uPassword);
	}

	@Override
	public String selectByAccount(String uAccount) {
		return userMapper.selectByAccount(uAccount);
	}
	
}
