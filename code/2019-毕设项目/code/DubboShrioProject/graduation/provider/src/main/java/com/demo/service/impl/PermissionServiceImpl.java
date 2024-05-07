package com.demo.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.domain.ResultInfo;
import com.demo.entity.Permission;
import com.demo.mapper.PermissionMapper;
import com.demo.service.IPermissionService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-02-01
 */
@com.alibaba.dubbo.config.annotation.Service(version = "${demo.service.version}", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
	@Autowired
	private PermissionMapper permissionMapper;
	
	//登录以及鉴权的时候  根据 用户名 获取权限信息
	@Override
	public List<Permission> findRolePerm(String nickName) {
		return permissionMapper.findRolePerm(nickName);
	}
	//获取权限分页信息,并附加模糊查询
	@Override
	public ResultInfo getPermPageList(String offset, String limit, String qry) {
		QueryWrapper<Permission> qw = new QueryWrapper<Permission>();
		if (StringUtils.isNoneBlank(qry)) {
			qw.like("name", qry);
		}
		Page<Permission> page = new Page<Permission>();
		page.setCurrent(Integer.parseInt(offset));
		page.setSize(Integer.parseInt(limit));
		IPage<Permission> page1 = permissionMapper.selectPage(page, qw);
		return new ResultInfo(page1.getTotal(), page1.getRecords());
	}

}
