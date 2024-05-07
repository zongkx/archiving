package com.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Map<String,Object> map) {
		map.put("test", "freemarker");
		map.put("list", userService.selectAll());
		return "test";
		
	}
	
	@RequestMapping("/users")
	public @ResponseBody Object users() {
		return userService.selectAll();
	}
}
