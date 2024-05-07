package com.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Behavior;
import com.demo.mapper.BehaviorMapper;
import com.demo.service.IBehaviorService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-05-12
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Service
public class BehaviorServiceImpl extends ServiceImpl<BehaviorMapper, Behavior> implements IBehaviorService {
	@Autowired
	private BehaviorMapper behaviorMapper;
	
	@Override
	public List<Long> getSoftwareId(String nickname) {
		QueryWrapper<Behavior> qw = new QueryWrapper<>();
		qw.eq("nickname", nickname);
		qw.notBetween("score", 1, 3);
		List<Behavior> list = behaviorMapper.selectList(qw);
		Set<Long> rSet = new HashSet<>();
		for(Behavior b :list) {
			rSet.add(b.getSoftwareId());
		}
		List<Long> res = new ArrayList<Long>(rSet);
		return res;
	}

	@Override
	public String checkDoCollection(String sid, String nickname) {
		QueryWrapper<Behavior> qw = new QueryWrapper<>();
		qw.eq("score", -1);
		qw.eq("software_id", sid);
		qw.eq("nickname", nickname);
		if(baseMapper.selectOne(qw)!=null) {
			return  "1";//已收藏
		}else {
			return  "-1";//未收藏
		}
	}

	@Override
	public boolean deCollection(String sid, String nickname) {
		QueryWrapper<Behavior> qw = new QueryWrapper<>();
		qw.eq("score", -1);
		qw.eq("software_id", sid);
		qw.eq("nickname", nickname);
		behaviorMapper.delete(qw);
		return true;
	}

}
