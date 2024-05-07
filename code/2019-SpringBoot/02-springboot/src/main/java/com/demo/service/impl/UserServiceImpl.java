package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 注入一个spring boot 配置好的 RedisTemplate
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectAll() {// 使用Redis缓存
		// 字符串序列化器
		@SuppressWarnings("rawtypes")
		RedisSerializer redisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);// 对key进行String序列化,增强可读性

		// 查询缓存
		// 问题:在高并发的情况下,出现缓存穿透的问题
		List<User> list = (List<User>) redisTemplate.opsForValue().get("allUsers");

		// 双重检测
		if (null == list) {
			synchronized (this) {

				list = (List<User>) redisTemplate.opsForValue().get("allUsers");
				if (list == null) {
					System.out.println("==========================DB==");
					// 查询为空 再查询一次
					list = userMapper.selectAll();
					// 放到redis 缓存中
					redisTemplate.opsForValue().set("allUsers", list);
				} else {
					System.out.println("===========Redis=================");
				}
			}
		} else {
			System.out.println("===========Redis=================");
		}
		return list;
	}

	@Override
	public User selectById(Integer id) {
		return userMapper.selectById(id);
	}

	@Transactional // 开启Spring事务
	@Override
	public int updateUser(String username, String password, Integer id) {
		int update = userMapper.updateUser(username, password, id);
		System.out.println("更新的结果" + update);

		// 模拟一个 运行时异常 ,来模拟事务出现异常的回滚
		@SuppressWarnings("unused")
		int a = 10 / 0;

		return update;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int insertUser(String username, String password) {
		// 字符串序列化器
		@SuppressWarnings("rawtypes")
		RedisSerializer redisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);// 对key进行String序列化,增强可读性

		// 查询缓存
		// 问题:在高并发的情况下,出现缓存穿透的问题
		List<User> list = (List<User>) redisTemplate.opsForValue().get("allUsers");

		// 双重检测
		if (null == list) {
			synchronized (this) {
				list = (List<User>) redisTemplate.opsForValue().get("allUsers");
				if (list == null) {
					// 查询为空 再查询一次
					list = userMapper.selectAll();
					// 放到redis 缓存中
					redisTemplate.opsForValue().set("allUsers", list);
				}
			}
		}
		int flag = 1;

		for (int i = 0; i < list.size(); i++) {
			if (username.equals(list.get(i).getUsername())) {
				flag = 0;
				break;
			}
			
		}
		if (flag == 1) {
			userMapper.insertUser(username, password);
		}
		return flag;

	}
}
