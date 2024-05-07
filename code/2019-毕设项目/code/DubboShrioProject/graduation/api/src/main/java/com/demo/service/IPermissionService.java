package com.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.domain.ResultInfo;
import com.demo.entity.Permission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-02-01
 */

public interface IPermissionService extends IService<Permission> {
	List<Permission> findRolePerm(String nickName);
	
	//查询所有权限信息 
	ResultInfo getPermPageList(String offset, String limit, String qry);
}
