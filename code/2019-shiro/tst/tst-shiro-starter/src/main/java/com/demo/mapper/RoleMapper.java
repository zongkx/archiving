package com.demo.mapper;

import com.demo.entity.Role;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String nickName);
}
