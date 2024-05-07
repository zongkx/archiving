package com.dubbo.service;

import com.dubbo.model.User;

public interface UserService {
	public String sayHi(String name);
	public User selectByAccountAndPassword(String uAccount,String uPassword);
	public String selectByAccount(String uAccount);
}
