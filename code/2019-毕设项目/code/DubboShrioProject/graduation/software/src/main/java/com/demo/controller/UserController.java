package com.demo.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.entity.User;
import com.demo.service.IRoleService;
import com.demo.service.IUserRoleService;
import com.demo.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Reference(version = "1.0.0")
	private IUserService iUserService;
	
	@Reference(version = "1.0.0")
	private IRoleService iRoleService;
	
	@Reference(version = "1.0.0")
	private IUserRoleService iUserRoleService;
	
	@RequestMapping(value = { "/changeUser" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public Json changeUser(@RequestBody User user) {// 修改用户
		String oper = "change User Info";
		if(this.iUserService.updateUser(user)) {
			log.info(oper);
			return Json.succ(oper).data(user);
		}
		return Json.fail().data("Unknown Error");

	}
	
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public @ResponseBody ResultInfo UserList(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "5";
		}
		Page<User> page = new Page<User>();
		page.setCurrent(Integer.parseInt(offset));
		page.setSize(Integer.parseInt(limit));
		IPage<User> page1 = iUserService.page(page);
		log.info(page1.getRecords().toString());
		List<User> userList = page1.getRecords();
		for(User user:userList) {
			user.setUserRoles(iRoleService.findUserRole(user.getNickname()));
		}
		return new ResultInfo(page1.getTotal(), userList);

	}
	@RequestMapping(value = { "/apply" }, method = { RequestMethod.POST })
	public @ResponseBody Json apply(@RequestParam(value = "id", required = false) String id) {
		User u = new User();
		u.setApply(1L);//设置标志位为 1 代表正在申请中
		u.setId(Integer.parseInt(id));
		this.iUserService.updateById(u);
		return Json.succ();
	}
//	@RequestMapping("/modifyUser")
//	public @ResponseBody Json modifyUser(@RequestBody User user) {
//		log.info(user.toString());
//		User oldUser = this.iUserService.getById(user.getId());
//		boolean flag = this.iUserService.updateById(user);
//		this.iUserRoleService.updateUserRole(user);
//		log.info(flag + "  " + "this is modify user");
//		return Json.succ();
//	}
//	@RequestMapping(value = { "/delUsers" }, method = { RequestMethod.POST })
//	public @ResponseBody Json delUsers( String idcard) {
//		List<String> list = Arrays.asList(idcard.split(","));//删除的用户 用  逗号 来拆分
//		boolean flag = this.iUserService.removeByIds(list);
//		log.info( flag+"  " + "this is del users");
//		return Json.succ();
//	}
}
