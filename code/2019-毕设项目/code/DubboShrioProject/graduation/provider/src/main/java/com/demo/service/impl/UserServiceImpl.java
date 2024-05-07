package com.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.User;
import com.demo.entity.UserRole;
import com.demo.entity.UserWithRole;
import com.demo.mapper.RoleMapper;
import com.demo.mapper.UserMapper;
import com.demo.mapper.UserRoleMapper;
import com.demo.service.IUserRoleService;
import com.demo.service.IUserService;
import com.demo.utils.MD5Utils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-08
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout= 300000
)
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;

	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	IUserRoleService iUserRoleService;

	
	// 注册用户(不需要权限)
	public void registUser(User user) {
		user.setCreateTime(LocalDateTime.now());
		user.setPswd(MD5Utils.encrypt(user.getPswd()));
		user.setStatus(1L);
		this.save(user);
		UserRole ur = new UserRole();
		ur.setUserId(user.getId());
		ur.setRoleId(2);// 注册用户设置默认角色:  2   customer  注册用户    
		this.userRoleMapper.insert(ur);

	}
	//注册用户检查
	public boolean checkNickName(String nickname) {
		QueryWrapper<User> qw = new QueryWrapper<User>();//存在优化的可能 : 改类需要频繁实例化
		qw.eq("nickname", nickname);
		return (this.userMapper.findByNickName(nickname))==null ? true : false;//
	}
	
	
	// 添加用户(管理权限)
	public void addUser(User user, Integer[] roles) {
		user.setCreateTime(LocalDateTime.now());
		user.setStatus(1L);
		user.setPswd(MD5Utils.encrypt(user.getPswd()));//密码MD5加密
		this.save(user);//先更新 user表中的数据,
		this.setUserRoles(user.getId(), roles);//再添加 user_role 表中的数据
	}

	// 添加用户时添加相应的角色ID到 USER_ROLE中
	private void setUserRoles(Integer userId, Integer[] roles) {
		Arrays.stream(roles).forEach(roleId -> {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			this.userRoleMapper.insert(ur);
		});
	}

	// 修改用户
	public void updateUser(User user, Integer[] roles) {
		user.setPswd(null);
		user.setNickname(null);
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("id", user.getId());
		super.update(user, updateWrapper);//先更新 User 表 中的信息
		QueryWrapper<UserRole> qw = new QueryWrapper<UserRole>();
		qw.eq("user_id", user.getId());
		this.userRoleMapper.delete(qw);// 先根据User ID 删除 user_role表中对应的行 
		this.setUserRoles(user.getId(), roles);// 再重新插入 对应的 数据
	}
	@Transactional
	//删除用户
	public void deleteUsers(String userIds) {
		List<String> list = Arrays.asList(userIds.split(","));//删除的用户 用  逗号 来拆分
		super.removeByIds(list);//删除用户  USER 表中的数据 
		iUserRoleService.deleteUserRoleByUserId(userIds);//删除 user_role 表中的数据(根据用户的id)
	}
	//更新用户登录时间  last_login_time
	public void updateLoginTime(String nickname) {
		User user =new User();
		user.setNickname(nickname);
		user.setLastLoginTime(LocalDateTime.now());
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("nickname", nickname);
		super.update(user, updateWrapper);
		
	}
	//更新用户密码
	public void updatePswd(User user) {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		user.setPswd(MD5Utils.encrypt(user.getPswd()));
		updateWrapper.eq("nickname", user.getNickname());
		super.update(user, updateWrapper);
	}


	 



	//通过UserId 获取 用户对应的角色信息 放到User.userRoles
//	@Override
//	public User selectRoleByUserId(Integer id) {
//		return userMapper.selectRoleByUserId(id);
//	}




	@Override
	public List<UserWithRole> findUserWithRole(Integer userId) {
		return userMapper.findUserWithRole(userId);
	}




	@Override
	public User findByNickName(String nickname) {
		return userMapper.findByNickName(nickname);
	}
	@Override
	public List<User> selectUserPage(Integer offset, Integer limit) {
		 
		return userMapper.selectUserPage(offset, limit);
	}
	@Override
	public Integer selectUserPageTotal() {
		 
		return userMapper.selectUserPageTotal();
	}
	@Override
	public Boolean updateUser(User user) {//修改密码
		user.setPswd(MD5Utils.encrypt(user.getPswd()));// 密码加密
		QueryWrapper<User> qw = new QueryWrapper<User>();
		qw.eq("nickname", user.getNickname());
		super.update(user, qw);
		return true;
	}
	
}
