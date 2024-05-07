package com.demo.service;

import com.demo.entity.User;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-01-08
 */

public interface IUserService extends IService<User> {
	public void updateLoginTime(String nickname);
}
