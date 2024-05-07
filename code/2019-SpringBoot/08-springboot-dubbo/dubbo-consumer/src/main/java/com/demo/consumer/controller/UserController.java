package com.demo.consumer.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.model.User;
import com.dubbo.service.UserService;

@Controller

public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Reference(version = "${demo.service.version}")
	private UserService userService;

	@ResponseBody
	@RequestMapping("/hello")
	public Object hello() throws IOException {
		String a = userService.sayHi("aaaaa");
		System.out.println("aaa");
		return a;
	}

	@RequestMapping("/login")
	public Object login(String uAccount, String uPassword, HttpSession session, Map<String, Object> map) {

		if (userService.selectByAccount(uAccount) != null) {
			User user = userService.selectByAccountAndPassword(uAccount, uPassword);
			if (null != user) {
				logger.info("控制台日志信息 用户" + uAccount + "登录成功");
				session.setAttribute("uId", user.getuId());
				session.setAttribute("uAccount", user.getuAccount());
				return "main";

			} else {
				map.put("msg", "错误的密码");
				map.put("account", uAccount);
				return "index";
			}
		}else {
			map.put("msg", "错误的账号");
			return "index";
		}
	}

	@RequestMapping("/2register")
	public String register() {
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/checkAccount")
	public Object checkAccount( String account) {
		if(userService.selectByAccount(account)!=null) {
			return "0";
		}else {
			return "1";
		}
		
		
	}

}
