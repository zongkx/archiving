package com.dubbo.service;

import com.dubbo.model.User;

public interface UserService {
	public String sayHi(String name);
	public User getUser(int id);
}
