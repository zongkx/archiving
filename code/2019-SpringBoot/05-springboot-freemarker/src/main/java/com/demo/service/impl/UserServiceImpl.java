package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}
	
	
	
	@Override
	public User selectById(Integer id) {
		return userMapper.selectById(id);
	}

	@Transactional//开启Spring事务
	@Override
	public int updateUser(String username, String password,Integer id) {
		int update = userMapper.updateUser(username, password,id);
		System.out.println("更新的结果"+update);
		
		//模拟一个 运行时异常 ,来模拟事务出现异常的回滚 
		@SuppressWarnings("unused")
		int a = 10/0;
		
		
		
		
		return update;
	}

}
