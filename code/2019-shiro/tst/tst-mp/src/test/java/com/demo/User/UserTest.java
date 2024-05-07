package com.demo.User;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.entity.UserRole;
import com.demo.entity.UserWithRole;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.service.impl.UserRoleServiceImpl;
import com.demo.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserRoleServiceImpl userRoleServiceImpl;
	
	@Test
	public void checkNickname() {
		 
		if(userServiceImpl.checkNickName("test04")) {
			System.out.println(" ok ");
		}else {
			System.out.println(" repeat ");
		}
		
		
	}
	@Test
	public void registUser() {
		User user =new User();
		user.setNickname("test01");
		user.setPswd("123456");
		user.setEmail("1@qq.com");
		userServiceImpl.registUser(user);
		this.list();
		
		
	}

	@Test
	public void addUser() {
		User user =new User();
		user.setNickname("test03");
		user.setPswd("123456");
		user.setEmail("1@qq.com");
		Integer [] roles = {1,2};
		userServiceImpl.addUser(user, roles);
		this.list();
		
		
	}
	
	@Test// 根据登录的用户  id updateUser
	public void updateUser() {
		User user =new User();
		user.setId(17);
		user.setNickname("test03");
		user.setPswd("123456");
		user.setEmail("22@qq.com");
		Integer [] roles = {2};
		userServiceImpl.updateUser(user, roles);
		this.printOne(17);
		
		
	}
	private void printOne(Integer id) {
		System.out.println(userServiceImpl.getById(id));
		QueryWrapper<UserRole> qw = new QueryWrapper<UserRole>();
		qw.eq("user_id", id);
		System.out.println(userRoleServiceImpl.getMap(qw));
	}
	
	@Test
	public  void list() {
		for(User u:userServiceImpl.list()) {
			System.out.println(u.toString());
		}
		for(UserRole ur:userRoleServiceImpl.list()) {
			System.out.println(ur.toString());
		}
			
	}
	@Test//根据 USER_ID 获取角色ID
	public void findUserWithRole() {
		List<UserWithRole> list =userMapper.findUserWithRole(1);
		for(UserWithRole a:list) {
			System.out.println(a.toString());
		}
		
		
	}
	@Test// 根据登录的用户 nickname  获取 对应的角色
	public void findUserRole() {
		List<Role> list =roleMapper.findUserRole("root");
		for(Role a:list) {
			System.out.println(a.toString());
		} 
		
		
	}
	@Test// 删除用户
	public void deleteUsers() {
		String  userIds = "2,17,18";//  或者 15,16,17  多个用户用 逗号隔开
		userServiceImpl.deleteUsers(userIds);
		this.list();
	}
	@Test// 更新登录时间
	public void udpateLoginTime() {
		userServiceImpl.updateLoginTime("root");
		this.list();
	}
	@Test// 更新登录时间
	public void udpatePassword() {
		User user = new User();
		user.setNickname("root");
		user.setPswd("123456");
		userServiceImpl.updatePswd(user);
		this.list();
	}
}
