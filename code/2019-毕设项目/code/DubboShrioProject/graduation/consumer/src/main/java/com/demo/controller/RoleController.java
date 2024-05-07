package com.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.entity.Permission;
import com.demo.entity.Role;
import com.demo.entity.RolePermission;
import com.demo.service.IPermissionService;
import com.demo.service.IRolePermissionService;
import com.demo.service.IRoleService;
import com.demo.service.IUserRoleService;

@RestController
@RequestMapping("/role")
@RequiresPermissions("/role")
public class RoleController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Reference(version = "1.0.0")
	private IPermissionService iPermissionService;

	@Reference(version = "1.0.0")
	private IRolePermissionService iRolePermissionService;

	@Reference(version = "1.0.0")
	private IRoleService iRoleService;

	@Reference(version = "1.0.0")
	private IUserRoleService iUserRoleService;

	@PostMapping("/roleList")
	public Json getRoleList() {
		log.info(" this is /role/roleList");
		List<Role> roleList = this.iRoleService.list();
		for (Role r : roleList) {
			List<RolePermission> rpList = this.iRolePermissionService.getRolePermissionListByRoleId(r.getId());
			List<Permission> pList = new ArrayList<Permission>();
			for (RolePermission rp : rpList) {
				Permission p = this.iPermissionService.getById(rp.getPid());
				pList.add(p);
			}
			r.setRolePerms(pList);
		}
		return Json.succ().data(roleList);
	}

	@PostMapping("/modifyRole")
	public Json saveRole(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "pids", required = false) String pids) {
		Role r = new Role();
		r.setId(Integer.parseInt(id));
		r.setName(name);
		r.setType(type);
		iRoleService.updateById(r);

		iRolePermissionService.delRolePermissionByRoleId(id);// 先删除原有的中间表信息
		List<RolePermission> list = new ArrayList<>();

		if (StringUtils.isNotEmpty(pids)) {
			List<String> l = Arrays.asList(pids.split(","));
			for (String pid : l) {
				RolePermission rp = new RolePermission();
				rp.setRid(Long.parseLong(id));
				rp.setPid(Long.parseLong(pid));
				list.add(rp);
			}
			iRolePermissionService.saveBatch(list);// 新建所有的中间表信息
		}

		return Json.succ().data("");
	}

	@PostMapping("/addRole")
	public Json addRole(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "pids", required = false) String pids) {
		Role r = new Role();
		r.setName(name);
		r.setType(type);
		Long rid =iRoleService.addRole(r).longValue();
		
		List<RolePermission> list = new ArrayList<>();

		if (StringUtils.isNotEmpty(pids)) {
			List<String> l = Arrays.asList(pids.split(","));
			for (String pid : l) {
				RolePermission rp = new RolePermission();
				rp.setRid(rid);
				rp.setPid(Long.parseLong(pid));
				list.add(rp);
			}
			iRolePermissionService.saveBatch(list);// 新建所有的中间表信息
		}
		return Json.succ().data("");
	}

	@PostMapping("/delRole")
	public Json delRole(@RequestParam(value = "rid", required = false) String rid) {

		if (iUserRoleService.checkRoleHasUsed(rid)) {
			iRoleService.removeById(rid);
			iRolePermissionService.delRolePermissionByRoleId(rid);
			return Json.succ().data("");
		} else {
			return Json.fail("当前角色已被用户使用,不可以删除");
		}

	}
}
