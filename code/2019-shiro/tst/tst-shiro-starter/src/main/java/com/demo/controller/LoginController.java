package com.demo.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.demo.domain.Json;
import com.demo.domain.ResponseBo;
import com.demo.entity.User;
import com.demo.service.IUserService;
import com.demo.utils.MD5Utils;

@Controller
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IUserService userService;
	
	
	

	@RequestMapping("/index")
	public String toIndex(Map<String, Object> map) {
		// 获取到用户信息;
		if (SecurityUtils.getSubject() != null) {
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			map.put("user", user);
		}
		return "index.html";
	}

	@RequestMapping(value = { "/403" }, method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Json forbid() {
		return Json.fail("msg", "no login");
	}
	@GetMapping("/login")
	public String toLogin() {
		return "login.html";
	}
	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json login(@RequestBody User user) {
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
		pswd = MD5Utils.encrypt(nickname.toLowerCase(), pswd);//密码MD5加密
		UsernamePasswordToken token = new UsernamePasswordToken(nickname, pswd);
		try {
			// 登录
			Subject subject = SecurityUtils.getSubject();
			// 从session取出用户信息
			if (subject != null) {
				subject.logout();
			}
			subject.login(token);//shiro 认证
			this.userService.updateLoginTime(nickname);//更新最近一次登录时间
			return Json.succ(oper).data("nickname", nickname);

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

	@RequestMapping(value = { "/jsonTest" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Json jsonTest(@RequestBody User user) {
		JSONObject responseObj = (JSONObject) JSONObject.toJSON(user);
		log.info(responseObj.toString());
		return Json.fail("name", "jsonTest");
	}

}
