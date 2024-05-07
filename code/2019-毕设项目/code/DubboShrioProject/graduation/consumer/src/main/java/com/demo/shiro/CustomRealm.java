package com.demo.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.Permission;
import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.service.IPermissionService;
import com.demo.service.IRoleService;
import com.demo.service.IUserService;

public class CustomRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger(CustomRealm.class);

	@Reference(version = "1.0.0")
	private IUserService iUserService;
	@Reference(version = "1.0.0")
	private IRoleService iRoleService;
	@Reference(version = "1.0.0")
	private IPermissionService iPermissionService;
	//定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info("------------权限认证---------");
		User user = (User) getAvailablePrincipal(principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		List<Role> list = this.iRoleService.findUserRole(user.getNickname());
		Set<String> set = new HashSet<String>();
		for (Role r : list) {
			set.add(r.getName());
		}		
		
		Set<String> perms = new HashSet<String>();
		List<Permission> list1 = this.iPermissionService.findRolePerm(user.getNickname());
		for(Permission p : list1) {
			perms.add(p.getUrl());
		}
		info.setRoles(set);//添加角色集合   @RequireRoles("admin")会到info中寻找 字符串 "admin"
		info.setStringPermissions(perms);// 添加权限集合 @RequiresPermissions("test") 会到info中寻找字符串"test"
		return info;
	}

	// 定义如何获取用户信息的业务逻辑，给shiro做登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		log.info("------------身份认证方法---------");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String nickname = token.getUsername();
		if (nickname == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		User user = this.iUserService.findByNickName(nickname); // 从数据库获取对应用户名密码的用户
		if (user == null) {
			throw new UnknownAccountException("No account found for admin [" + nickname + "]");
		}
		if(user.getStatus() == 0) {
			throw new LockedAccountException("您的账号被禁止登录,请联系管理员");
		}
		// 查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
		// SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
		/*
		 * Set<String> roles = new HashSet<>(); roles.add("admin");
		 * user.setUserRoles(roles); Set<String> perms = new HashSet<>();
		 * perms.add("/user"); user.setUserRoles(perms);
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPswd(), getName());

		return info;
	}

}
