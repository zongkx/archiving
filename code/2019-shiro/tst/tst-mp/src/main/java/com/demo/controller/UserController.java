package com.demo.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.domain.Json;
import com.demo.entity.User;
import com.demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping("/UserList")
	@ResponseBody
	public String UserList() {
		String oper ="UserList";
		Page<User> userPage = new Page<User>();
		userPage.setCurrent(1);
		userPage.setSize(3);

		List<User> list = userService.page(userPage).getRecords();
		String str = JSON.toJSONString(list); // List转json
		System.out.println(str);
		return str;

	}

	@RequiresRoles("admin") // 该注解会触发 doGetAuthorizationInfo 进行权限认证操作,
							// 当用户没有登录(进行身份认证),访问该方法会触发
							// shiroFilterFactoryBean.setUnauthorizedUrl("/403");
	@RequiresPermissions("del/User")
	@RequestMapping(value = { "/test" }, method = { RequestMethod.GET })
	@ResponseBody
	public Json UserTest() {
		log.info("===============Test=================");
		// 利用方法上的注解或者SecurityUtils.getSubject().isPermitted();均可以触发权限认证
		return Json.succ("name", "xxx");
	}

	@RequiresRoles("admin")
	@RequiresPermissions("del/User")
	@RequestMapping(value = { "/test1" }, method = { RequestMethod.GET })
	@ResponseBody
	public Json UserTest1() {
		log.info("===============Test=================");
		return Json.succ("name", "xxx");
	}

}
