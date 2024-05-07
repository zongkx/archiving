package com.demo.service.impl;

import com.demo.entity.UserRole;
import com.demo.mapper.UserRoleMapper;
import com.demo.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
	
	
	
	public void deleteUserRoleByUserId(String userIds) {
		List<String> list = Arrays.asList(userIds.split(",")); // 用 逗号 分开
		super.removeByIds(list);
	}
	public void deleteUserRoleByRoleId(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		super.removeByIds(list);
	}
}
