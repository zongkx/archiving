package com.demo.provider.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.model.User;
import com.dubbo.service.UserService;

@Component

@Service(
		interfaceClass=UserService.class,version="1.0.0"
		)
public class UserServiceImpl implements UserService{
	

	@Override
	public User getUser(int id) {
		return null;
	}

	@Override
	public String sayHi(String name) {
		return "hello dubbo";
	}
	
}
