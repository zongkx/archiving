package com.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Reference(version = "1.0.0")
	private IUserService iUserService;
	
	@Reference(version = "1.0.0")
	private IRoleService iRoleService;
	
	
	@Reference(version = "1.0.0")
	private IPermissionService iPermissionService;

	@RequestMapping("/logout")
	public @ResponseBody Json logout() {
		log.info("==========================/logout==================================");
		SecurityUtils.getSubject().logout();
		return Json.succ();

	}

	@RequestMapping("/index")
	public @ResponseBody Json toIndex(Map<String, Object> map) {
		log.info("==========================/index==================================");
		// 获取到用户信息;
		if (SecurityUtils.getSubject() != null) {
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			map.put("user", user);
			if (SecurityUtils.getSubject().hasRole("admin")) {// 如果用户角色 是Admin 则跳转到后台管理页面
				return Json.succ().data(user);
			} else {
				User u = new User();
				u.setNickname("游客");
				map.put("user", u);
				return Json.succ().data(u);
			}
		} else {
			User u = new User();
			u.setNickname("游客");
			map.put("user", u);
			return Json.succ().data(u);
		}

	}

	@RequestMapping(value = { "/403" }, method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Json forbid() {
		log.info("==========================/403==================================");
		return Json.fail("msg", "no login");
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Json login(@RequestBody User user) {
		log.info("==========================/login==================================");
		String oper = "user login : ";
		JSONObject responseObj = (JSONObject) JSONObject.toJSON(user);
		log.info(oper + responseObj);
		String nickname = responseObj.getString("nickname");
		String pswd = responseObj.getString("pswd");
		if (StringUtils.isEmpty(nickname)) {
			return Json.fail(oper, "用户名不能为空");
		}
		if (StringUtils.isEmpty(pswd)) {
			return Json.fail(oper, "密码不能为空");
		}
		pswd = MD5Utils.encrypt(pswd);// 密码MD5加密
		UsernamePasswordToken token = new UsernamePasswordToken(nickname, pswd);
		try {
			// 登录
			Subject subject = SecurityUtils.getSubject();
			// 从session取出用户信息
			if (subject != null) {
				subject.logout();
			}
			subject.login(token);// shiro 认证
			this.iUserService.updateLoginTime(nickname);// 更新最近一次登录时间
			User user0 = (User) SecurityUtils.getSubject().getPrincipal();
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
			
			return Json.succ(oper).data(user0);

		} catch (UnknownAccountException uae) {
			log.warn("用户帐号不正确");
			return Json.fail(oper, "用户帐号或密码不正确");

		} catch (IncorrectCredentialsException ice) {
			log.warn("用户密码不正确");
			return Json.fail(oper, "用户帐号或密码不正确");

		} catch (LockedAccountException lae) {
			log.warn("用户帐号被锁定");
			return Json.fail(oper, "用户帐号被锁定不可用");

		} catch (AuthenticationException ae) {
			log.warn("登录出错");
			return Json.fail(oper, "登录失败：" + ae.getMessage());
		}

	}

	@RequiresGuest
	@RequestMapping(value = { "/registUser" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json registUser(@RequestBody User user) {
		log.info("==========================/registUser==================================");
		String oper = "user regist : ";
		if (iUserService.checkNickName(user.getNickname())) {
			user.setCreateTime(LocalDateTime.now());
			iUserService.registUser(user);
		} else {
			return Json.fail(oper, "密码不能为空");
		}
		return Json.succ(oper).data("nickname", user.getNickname());

	}

	@RequestMapping(value = { "/changeUser" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public @ResponseBody Json changeUser(@RequestBody User user) {// 修改用户
		String oper = "change User Info";
		if (this.iUserService.updateUser(user)) {
			log.info(oper);
			return Json.succ(oper).data(user);
		}
		return Json.fail().data("Unknown Error");

	}

}
