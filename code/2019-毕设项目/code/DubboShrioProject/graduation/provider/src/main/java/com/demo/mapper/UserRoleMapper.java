package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.UserRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@Mapper

public interface UserRoleMapper extends BaseMapper<UserRole> {
	
}
