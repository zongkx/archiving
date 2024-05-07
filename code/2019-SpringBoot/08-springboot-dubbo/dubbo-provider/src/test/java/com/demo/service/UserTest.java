package com.demo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.DubboProviderApplicationTests;
import com.demo.dao.UserMapper;

public class UserTest extends DubboProviderApplicationTests{
	
	@Autowired
	private UserMapper um;
	
	@Test
	public void a () {
		String acc = "111";
		String password ="123";
		
		System.out.println(um.selectByAccountAndPassword(acc, password));;
	}
	@Test
	public void b () {
		
		System.out.println(um.selectByAccount("110"));
		
	}
}
