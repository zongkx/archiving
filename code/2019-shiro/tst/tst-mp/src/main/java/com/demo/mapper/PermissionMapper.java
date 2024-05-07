package com.demo.mapper;

import com.demo.entity.Permission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-02-01
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
	List<Permission> findRolePerm(String nickName);
}
