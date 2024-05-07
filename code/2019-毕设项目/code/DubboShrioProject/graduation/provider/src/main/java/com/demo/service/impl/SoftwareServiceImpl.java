package com.demo.service.impl;

import com.demo.domain.ResultInfo;
import com.demo.entity.Behavior;
import com.demo.entity.Software;
import com.demo.mapper.BehaviorMapper;
import com.demo.mapper.SoftwareMapper;
import com.demo.service.ISoftwareService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-03-21
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Service
public class SoftwareServiceImpl extends ServiceImpl<SoftwareMapper, Software> implements ISoftwareService {

	@Autowired
	private SoftwareMapper softwareMapper;
	
	@Autowired
	private BehaviorMapper behaviorMapper;
	
	public List<Software> getSoftwareByScoreI() {
		return this.softwareMapper.getSoftwareByScore();
	}


	@Override
	public ResultInfo getSoftwarePageByType(String offset, String limit, String type) {
		Page<Software> p =new Page<>();
		p.setCurrent(Long.parseLong(offset));
		p.setSize(Long.parseLong(limit));
		QueryWrapper<Software> q = new QueryWrapper<Software>();
		q.eq("type", type);
		IPage<Software> ip = super.page(p,q);
		return  new ResultInfo(ip.getTotal(), ip.getRecords());
	}
	@Override
	public ResultInfo getSoftwarePageByTitle(String offset, String limit, String title) {
		Page<Software> p =new Page<>();
		p.setCurrent(Long.parseLong(offset));
		p.setSize(Long.parseLong(limit));
		QueryWrapper<Software> q = new QueryWrapper<Software>();
		if(StringUtils.isNotBlank(title)) {
			q.like("title", title);
		}
		IPage<Software> ip = super.page(p,q);
		return  new ResultInfo(ip.getTotal(), ip.getRecords());
	}
	@Override
	public ResultInfo getSoftwarePageByTitleAndAid(String offset, String limit, String title,String aid) {
		Page<Software> p =new Page<>();
		p.setCurrent(Long.parseLong(offset));
		p.setSize(Long.parseLong(limit));
		QueryWrapper<Software> q = new QueryWrapper<Software>();
		if(StringUtils.isNotBlank(title)) {
			q.like("title", title);
		}
		q.eq("user_id", aid);
		IPage<Software> ip = super.page(p,q);
		return  new ResultInfo(ip.getTotal(), ip.getRecords());
	}


	@Override
	public List<Object> getSfAlltype() {
		QueryWrapper<Software> q = new QueryWrapper<Software>();
		q.select("id","type");
		q.groupBy("type");
		return this.softwareMapper.selectObjs(q);
	}


	@Override
	public ResultInfo getUserCollection(String offset, String limit, String nickname) {
		QueryWrapper<Behavior> qw = new QueryWrapper<Behavior>();
		qw.eq("nickname", nickname);
		qw.eq("score", -1);
		List<Behavior> list = behaviorMapper.selectList(qw);
		if(list.size()>0) {
			IPage<Software> iPage = new Page<Software>();
			iPage.setCurrent(Long.parseLong(offset));
			iPage.setSize(Long.parseLong(limit));
			List<Long> sids = new ArrayList<Long>();
			for(Behavior b :list) {
				sids.add(b.getSoftwareId());
			}
			QueryWrapper<Software> sQw = new QueryWrapper<>();
			sQw.in("id", sids);
			IPage<Software> ip = softwareMapper.selectPage(iPage,sQw);
			return new ResultInfo(ip.getCurrent(), ip.getRecords());
			
		}else {
			return new ResultInfo(0, null);
		}
	}

}
