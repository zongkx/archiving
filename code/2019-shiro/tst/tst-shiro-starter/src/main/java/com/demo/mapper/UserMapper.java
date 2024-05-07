package com.demo.mapper;

import com.demo.entity.User;
import com.demo.entity.UserWithRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-01-09
 */
public interface UserMapper extends BaseMapper<User> {
	int test();
	
	IPage<User> selectUserPage(Page page, @Param("status") Integer status);
	
	User selectRoleByUserId(Integer id);
	
	List<UserWithRole> findUserWithRole(Integer userId);
	
	User findByNickName(String nickname);
}
