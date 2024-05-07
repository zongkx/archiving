package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.model.User;

@Mapper // 必要的注解,相当于配置文件中spring 中配置bean
public interface UserMapper {
	List<User> selectAll();
	User selectById(Integer id);
	int updateUser(String username,String password,Integer id);
	int insertUser(String username,String password);
}
