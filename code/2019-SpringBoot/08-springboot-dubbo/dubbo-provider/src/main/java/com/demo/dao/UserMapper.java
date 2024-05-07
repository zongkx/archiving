package com.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.dubbo.model.User;
@Mapper // 必要的注解,相当于配置文件中spring 中配置bean
public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByAccountAndPassword(String uAccount,String uPassword);
    
    String selectByAccount(String uAccount);
}