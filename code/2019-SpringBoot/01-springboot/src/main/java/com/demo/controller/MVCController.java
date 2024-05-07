package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;

@RestController
// = @Controller + @Responsebody
public class MVCController {
	
	
	@RequestMapping(value="/boot/getUser",method=RequestMethod.GET )
	public Object getUser() {
		User user = new User();
		user.setId(100);
		user.setUsername("Raynor");
		return user;
		
	}
	@GetMapping("/boot/getUser1")
	//只支持get请求
	//相当于上面的requestMapping 加上 method=RequestMethod.GET
	public Object getUser1() {
		User user = new User();
		user.setId(100);
		user.setUsername("Raynor");
		return user;
		
	}
	@PostMapping("/boot/getUser2")
	//只支持post请求
	//相当于上面的requestMapping 加上 method=RequestMethod.POST
	//直接在地址栏输入地址相当于get请求,这里会报405错误,不支持该请求
	public Object getUser2() {
		User user = new User();
		user.setId(100);
		user.setUsername("Raynor");
		return user;
		
	}
	
	
}
