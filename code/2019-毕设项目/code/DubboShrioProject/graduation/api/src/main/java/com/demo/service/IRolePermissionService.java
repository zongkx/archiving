package com.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.RolePermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-05-02
 */
public interface IRolePermissionService extends IService<RolePermission> {
	
	public List<RolePermission> getRolePermission (float id);
	
	public int delRolePermissionByPermissionId(String ids);
	public int  delRolePermissionByRoleId(String rid);
	public List<RolePermission> getRolePermissionListByRoleId(float rid);
	

 }
