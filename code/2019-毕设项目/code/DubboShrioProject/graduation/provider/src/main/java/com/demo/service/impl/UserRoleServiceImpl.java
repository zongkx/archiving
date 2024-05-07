package com.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.UserRole;
import com.demo.mapper.UserRoleMapper;
import com.demo.service.IUserRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	//删除用户时,根据User_Id 批量删除  User_role表中的 数据
	public void deleteUserRoleByUserId(String userIds) {
		List<String> list = Arrays.asList(userIds.split(",")); // 用 逗号 分开
		super.removeByIds(list);
	}
	//根据Role_id  批量删除User_Role 表中的 数据
	public void deleteUserRoleByRoleId(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		super.removeByIds(list);
	}
	//修改用户信息时,根据User_id  更新Role 表中的数据
	public void updateUserRole(String userId,String roleId) {
		UpdateWrapper<UserRole> qw = new UpdateWrapper<UserRole>();
		qw.eq("user_id", userId); 
		UserRole ur = new UserRole();
		ur.setUserId(Integer.parseInt(userId));
		ur.setRoleId(Integer.parseInt(roleId));
		userRoleMapper.update(ur, qw);
	}
	//删除角色的时候,判断该角色是否被用户使用
	@Override
	public boolean checkRoleHasUsed(String rid) {

		QueryWrapper<UserRole> qw = new QueryWrapper<UserRole>();
		qw.eq("role_id", rid);
		UserRole ur = userRoleMapper.selectOne(qw);
		if(ur == null) {
			return true;
		}else {
		return false;}
	}
}
