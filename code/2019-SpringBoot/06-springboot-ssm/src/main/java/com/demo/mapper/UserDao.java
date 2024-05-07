package com.demo.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.model.User;
/**
 * 用户DAO层接口
 */
@Mapper
public interface UserDao {
	/**
	 * 通过账号和密码查询用户
	 */
	public User findUser(@Param("usercode") String usercode,
			                @Param("password") String password);
}
