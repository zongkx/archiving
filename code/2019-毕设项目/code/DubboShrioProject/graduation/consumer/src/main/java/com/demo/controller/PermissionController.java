package com.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.entity.Permission;
import com.demo.entity.RolePermission;
import com.demo.service.IPermissionService;
import com.demo.service.IRolePermissionService;
import com.demo.service.IRoleService;

@RestController
@RequestMapping("/perm")
@RequiresPermissions("/perm")
public class PermissionController {
	private static final Logger log = LoggerFactory.getLogger(PermissionController.class);

	@Reference(version = "1.0.0")
	private IPermissionService iPermissionService;

	@Reference(version = "1.0.0")
	private IRolePermissionService iRolePermissionService;

	@Reference(version = "1.0.0")
	private IRoleService IRoleService;

	@RequestMapping("/permPageList")
	public ResultInfo getPermPageList(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "qry", required = false) String qry) {
		log.info("==========================/perm/permPageList==================================");
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "5";
		}
		return iPermissionService.getPermPageList(offset, limit, qry);
	}

	@RequestMapping("/modifyPerm")
	public @ResponseBody Json modifyPerm(@RequestBody Permission permission) {
		log.info("==========================/perm/modifyPerm==================================");
		this.iPermissionService.updateById(permission);
		return Json.succ();
	}

	@RequestMapping("/addPerm")
	public @ResponseBody Json addPerm(@RequestBody Permission permission) {
		log.info("==========================/perm/addPerm==================================");

		this.iPermissionService.save(permission);
		return Json.succ();
	}

	@RequestMapping("/delPerm")
	public @ResponseBody Json delPerm(@RequestParam String ids) {
		log.info("==========================/perm/delPerm==================================");
		List<String> idList = Arrays.asList(ids.split(","));

		Collection<Permission> list = this.iPermissionService.listByIds(idList);
		Set<String> roleSet = new HashSet<String>();
		for (Permission p : list) {
			List<RolePermission> rp = new ArrayList<RolePermission>();
				rp=	this.iRolePermissionService.getRolePermission(p.getId());
			if(rp.size()>0) {
				for(RolePermission r : rp) {
					roleSet.add(this.IRoleService.getById(r.getRid()).getName());
				}
			}
		}
		return Json.succ().data(roleSet);
	}
	@RequestMapping("/delPermConfirm")
	public @ResponseBody Json delPermConfirm(@RequestParam String ids) {
		log.info("==========================/perm/delPermConfirm==================================");
		List<String> idList = Arrays.asList(ids.split(","));
		this.iPermissionService.removeByIds(idList);
		this.iRolePermissionService.delRolePermissionByPermissionId(ids);
		return Json.succ();
	}
}
