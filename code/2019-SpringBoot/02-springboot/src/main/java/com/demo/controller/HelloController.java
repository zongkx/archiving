package com.demo.controller;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.service.UserService;

@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@GetMapping("/boot/getUsers")
	public @ResponseBody Object users() {
//		多线程测试  缓存穿透
		//
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				userService.selectAll();
			}
		};

		ExecutorService executorService = Executors.newFixedThreadPool(25);
		for (int i = 0; i < 10000; i++) {
			executorService.submit(runnable);
		}

		return userService.selectAll();
	}

	@RequestMapping("/boot/hello")
	public @ResponseBody String hello() {
		return "hello world";
	}

	@RequestMapping("/reg")
	public String reg(Map<String, Object> map,String username,String password) {
		
		int a = userService.insertUser(username, password);
		if(a==1) {
			map.put("msg","ok");
			return "reg.jsp";
		}
		else {
			map.put("msg","error");
			return "index.jsp";
		}
		
	}
}
