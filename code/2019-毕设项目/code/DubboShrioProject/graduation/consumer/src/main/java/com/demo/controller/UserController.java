package com.demo.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.demo.entity.Comment;
import com.demo.entity.User;
import com.demo.entity.UserRole;
import com.demo.service.ICommentService;
import com.demo.service.IRoleService;
import com.demo.service.IUserRoleService;
import com.demo.service.IUserService;

@RestController
@RequestMapping("/user")
@RequiresPermissions("/user") // 访问该接口下的 所有方法都需要 权限: /user
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Reference(version = "1.0.0")
	private IUserService iUserService;

	@Reference(version = "1.0.0")
	private IRoleService iRoleService;

	@Reference(version = "1.0.0")
	private IUserRoleService iUserRoleService;
	
	
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;
	
	

	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public @ResponseBody ResultInfo UserList(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit) {
		log.info("==========================/user/userList==================================");
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
		for (User user : userList) {
			user.setUserRoles(iRoleService.findUserRole(user.getNickname()));
		}
		return new ResultInfo(page1.getTotal(), userList);

	}

	@RequestMapping("/modifyUser")
	public @ResponseBody Json modifyUser(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "nickname", required = false) String nickname) {
		log.info("==========================/user/modifyUser==================================");

		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setNickname(nickname);
		user.setStatus(Long.parseLong(status));
		if("6".equals(roleId)) {
			user.setApply(2L);
			Comment comment = new Comment();
			comment.setToUid(nickname);
			comment.setContent("恭喜您,已经通过审核");
			comment.setTime(LocalDateTime.now());
			comment.setType("3");//系统消息
			comment.setFlag("0");//未读
			comment.setFromUid(((User)SecurityUtils.getSubject().getPrincipal()).getNickname());
			this.iCommentService.save(comment);
		}else {
			user.setApply(0L);
		}
		
		iUserService.updateById(user);
		// 根据 user_id 修改中间表的 role_id
		iUserRoleService.updateUserRole(id, roleId);

		return Json.succ();
	}

	@RequestMapping(value = { "/delUsers" }, method = { RequestMethod.POST })
	public @ResponseBody Json delUsers(String idcard) {
		log.info("==========================/user/delUsers==================================");
		List<String> list = Arrays.asList(idcard.split(","));// 删除的用户 用 逗号 来拆分
		boolean flag = this.iUserService.removeByIds(list);
		log.info(flag + "  " + "this is del users");
		return Json.succ();
	}
	
	@RequestMapping(value = { "/apply" }, method = { RequestMethod.POST })
	public @ResponseBody Json apply(String idcard) {
		log.info("==========================/user/delUsers==================================");
		List<String> list = Arrays.asList(idcard.split(","));// 删除的用户 用 逗号 来拆分
		for(String id:list) {
			User u = new User();
			u.setApply(2L);
			u.setId(Integer.parseInt(id));
			this.iUserService.updateById(u);
			UserRole ur = new UserRole();
			ur.setUserId(Integer.parseInt(id));
			ur.setRoleId(6);//文章发布者
			this.iUserRoleService.save(ur);
		}
		return Json.succ();
	}
	
	
}
