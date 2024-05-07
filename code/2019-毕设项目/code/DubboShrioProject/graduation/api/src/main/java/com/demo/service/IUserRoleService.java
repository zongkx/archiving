package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.UserRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
public interface IUserRoleService extends IService<UserRole> {
	public void deleteUserRoleByUserId(String userIds);
	public void deleteUserRoleByRoleId(String roleIds);
	
	public void updateUserRole(String roleId,String userId);
	
	public boolean checkRoleHasUsed(String rid);
}
