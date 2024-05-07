package com.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.RolePermission;
import com.demo.mapper.RolePermissionMapper;
import com.demo.service.IRolePermissionService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-05-02
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(version = "${demo.service.version}", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
		implements IRolePermissionService {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	 
	//删除权限 的时候 根据权限P_id 获取 对应的表 Role_Permission 数据 
	@Override
	public List<RolePermission> getRolePermission(float id) {
		QueryWrapper<RolePermission> qw = new QueryWrapper<RolePermission>();
		qw.eq("pid", id);
		return rolePermissionMapper.selectList(qw);
	}


	//确认删除权限的时候,根据权限P_ID 批量删除  表 Role_Permission中的数据
	@Override
	public int delRolePermissionByPermissionId(String ids) {
		QueryWrapper<RolePermission> qw = new QueryWrapper<RolePermission>();
		qw.in("pid", Arrays.asList(ids.split(",")));
		return rolePermissionMapper.delete(qw);
	}
	//删除角色 以及保存角色之前 先根据 Role_Id  把表Role_Permission中旧的数据删除 
	@Override
	public int delRolePermissionByRoleId(String rid) {
		QueryWrapper<RolePermission> qw = new QueryWrapper<RolePermission>();
		qw.eq("rid", rid);
		return rolePermissionMapper.delete(qw);
	}

	//获取角色信息的时候 根据Role_ID  获取 表 Role_Permission中的数据
	@Override
	public List<RolePermission> getRolePermissionListByRoleId(float rid) {
		QueryWrapper<RolePermission> qw = new QueryWrapper<RolePermission>();
		qw.eq("rid", rid);
		return rolePermissionMapper.selectList(qw);
	}

}
