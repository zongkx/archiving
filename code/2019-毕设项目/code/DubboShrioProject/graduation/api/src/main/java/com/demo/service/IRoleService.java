package com.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
public interface IRoleService extends IService<Role> {
	List<Role> findUserRole(String nickName);
	
	Integer addRole(Role r);
	
//	List<Role> getRoleListByRoleId(float roleId);
}
