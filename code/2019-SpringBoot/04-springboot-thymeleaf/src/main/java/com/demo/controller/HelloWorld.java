package com.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	@RequestMapping("/hello")
	public String hello(Map<String,Object> map) {
		map.put("msg", "hello world");
		return "thymeleaf";
	}
}
