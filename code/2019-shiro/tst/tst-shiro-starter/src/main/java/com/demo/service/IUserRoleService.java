package com.demo.service;

import com.demo.entity.UserRole;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@Service
public interface IUserRoleService extends IService<UserRole> {
	public void deleteUserRoleByUserId(String userIds);
	public void deleteUserRoleByRoleId(String roleIds);
}
