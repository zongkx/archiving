package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.mapper.UserDao;
import com.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void contextLoads() {
		User u = new User();
		u.setUser_code("test");
		u.setUser_password("123");
		User u1 = userDao.findUser("test", "123");
		System.out.println(u1);
	}

}
