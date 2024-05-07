package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Role;
import com.demo.mapper.RoleMapper;
import com.demo.service.IRoleService;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	//登录以及鉴权的时候  根据用户名 获取其 表Role 中的角色信息
	@Override
	public List<Role> findUserRole(String nickName) {
		return roleMapper.findUserRole(nickName) ;
	}
	//添加角色的时候  返回对应主键
	@Override
	public Integer addRole(Role r) {
		roleMapper.insert(r);
		return r.getId();
	}

//	@Override
//	public List<Role> getRoleListByRoleId(float roleId) {
//		
//		QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
//		queryWrapper.eq("rid", roleId);
//		return roleMapper.selectList(queryWrapper);
//	}

}
