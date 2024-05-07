package com.demo.service;

import java.util.List;

import com.demo.model.User;

public interface UserService {
	public List<User> selectAll();
	public User selectById(Integer id);
	public int updateUser(String username,String password,Integer id);
	public int insertUser(String username,String password);
}
