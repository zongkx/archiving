package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Role;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-01-10
 */
@Mapper

public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String nickName);
}
