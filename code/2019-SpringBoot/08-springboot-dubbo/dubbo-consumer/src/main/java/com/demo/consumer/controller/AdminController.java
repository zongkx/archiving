package com.demo.consumer.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.service.AdminService;

@Controller

public class AdminController {


	@Reference(version = "${demo.service.version}")
	private AdminService adminService;

	@ResponseBody
	@RequestMapping("/admin")
	public Object hello() throws IOException {
		String a = adminService.sayHi("aaaaa");
		return a;
	}

	
}
