package com.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.domain.Json;
import com.demo.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;
	
	@RequiresRoles("admin")//该注解会触发 doGetAuthorizationInfo 进行权限认证操作,
						   //当用户没有登录(进行身份认证),访问该方法会触发
						  //shiroFilterFactoryBean.setUnauthorizedUrl("/403");
	@RequiresPermissions("test")
	@RequestMapping(value= {"/test"},method= {RequestMethod.GET} )
	@ResponseBody
	public Json UserTest() {
		log.info("===============Test=================");
		//利用方法上的注解或者SecurityUtils.getSubject().isPermitted();均可以触发权限认证
		return Json.succ("name","xxx");
	}
	@RequiresRoles("admin")
	@RequiresPermissions("test1")
	@RequestMapping(value= {"/test1"},method= {RequestMethod.GET} )
	@ResponseBody
	public Json UserTest1() {
		log.info("===============Test=================");
		return Json.succ("name","xxx");
	}
	
	
}
