package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.UserService;

@RestController
public class MybatisController {
	@Autowired
	private UserService userService;
	@GetMapping("/boot/getUsers")
	public Object users() {
		return  userService.selectAll();
	}
	@RequestMapping("/boot/TraTest")
	public Object tra() {
		String name = "aaa";
		String password = "123";
		return userService.updateUser(name, password,2);
	}
}
