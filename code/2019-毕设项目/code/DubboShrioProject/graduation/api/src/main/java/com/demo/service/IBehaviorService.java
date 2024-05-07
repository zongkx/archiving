package com.demo.service;

import com.demo.entity.Behavior;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Raynor
 * @since 2019-05-12
 */
public interface IBehaviorService extends IService<Behavior> {
	
	List<Long> getSoftwareId(String nickname);
	
	String checkDoCollection(String sid,String nickname);
	
	boolean deCollection(String sid,String nickname);
}
