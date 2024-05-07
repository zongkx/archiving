package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import com.demo.entity.UserWithRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Raynor
 * @since 2019-01-09
 */
@Mapper

public interface UserMapper extends BaseMapper<User> {
	int test();
	
//	IPage<User> selectUserPage(@SuppressWarnings("rawtypes") Page page);
	
	List<User> selectUserPage(Integer offset,Integer limit);
	Integer selectUserPageTotal();
	
//	User selectRoleByUserId(Integer id);
	
	List<UserWithRole> findUserWithRole(Integer userId);
	
	User findByNickName(String nickname);
}
