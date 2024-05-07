package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.CustomerService;

@RestController
public class HelloController {
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/hello")
	public Object hello() {
		return customerService.getCustomerById(1);
	}
}
