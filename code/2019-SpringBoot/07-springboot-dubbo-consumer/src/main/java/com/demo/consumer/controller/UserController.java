package com.demo.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.UserService;

@Controller

public class UserController {
	
	@Reference(
			version="1.0.0",url = "dubbo://127.0.0.1:20880")
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/hello")
	public Object hello() {
		
		return userService.sayHi("aaaaa");
	}
}
