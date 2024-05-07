package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.demo.domain.Json;
import com.demo.entity.Permission;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.service.IPermissionService;
import com.demo.service.IRoleService;
import com.demo.service.IUserService;
import com.demo.utils.MD5Utils;

@RestController
@RequestMapping("/base")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Reference(version = "1.0.0")
	private IUserService iUserService;
	
	@Reference(version = "1.0.0")
	private IRoleService iRoleService;
	
	
	@Reference(version = "1.0.0")
	private IPermissionService iPermissionService;

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public Json login(@RequestBody User user) {
		
		String oper = "user login : ";
		JSONObject responseObj = (JSONObject) JSONObject.toJSON(user);
		String nickname = responseObj.getString("nickname");
		String pswd = responseObj.getString("pswd");
		if (StringUtils.isEmpty(nickname)) {
			return Json.fail(oper, "用户名不能为空");
		}
		if (StringUtils.isEmpty(pswd)) {
			return Json.fail(oper, "密码不能为空");
		}
		pswd = MD5Utils.encrypt(pswd);// 密码MD5加密
		log.info(pswd + nickname);
		User user0 = this.iUserService.findByNickName(nickname); // 从数据库获取对应用户名密码的用户
		if (user0 != null) {
			if (pswd.equals(user0.getPswd())) {
				this.iUserService.updateLoginTime(nickname);// 更新最近一次登录时间
				//获取用户角色信息
				List<Role> roles = new ArrayList<Role>();
				List<Role> list = this.iRoleService.findUserRole(user.getNickname());
				for (Role r : list) {
					roles.add(r);
				}		
				//获取用户权限信息
				List<Permission> perms = new ArrayList<Permission>();
				List<Permission> list1 = this.iPermissionService.findRolePerm(user.getNickname());
				for(Permission p : list1) {
					perms.add(p);
				}
				user0.setUserRoles(roles);
				user0.setUserPerms(perms);
				
				return Json.succ().data(user0);
			} else {
				return Json.fail(oper, "密码错误");
			}

		} else {
			return Json.fail(oper, "用户不存在");
		}

	}

	@RequestMapping(value = { "/registUser" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public Json registUser(@RequestBody User user) {
		String oper = "user regist : ";
		log.info("this is " + oper);
		if (iUserService.checkNickName(user.getNickname())) {
			iUserService.registUser(user);
			return Json.succ(oper).data("nickname", user.getNickname());
		} else {
			return Json.fail(oper, "用户已存在");
		}
		

	}

}
