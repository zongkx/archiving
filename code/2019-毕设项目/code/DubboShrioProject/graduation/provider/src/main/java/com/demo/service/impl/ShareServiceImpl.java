package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.domain.ResultInfo;
import com.demo.entity.Share;
import com.demo.mapper.ShareMapper;
import com.demo.service.IShareService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-04-04
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        timeout= 300000
)
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {
	@Autowired
	private ShareMapper sm;
	
	
	@Override
	public ResultInfo getSharePageByUserId(String offset, String limit, String userId) {
		Page<Share> p = new Page<>();
		p.setCurrent(Long.parseLong(offset));
		p.setSize(Long.parseLong(limit));
		QueryWrapper<Share> qw = new QueryWrapper<Share>();
		qw.eq("user_id", userId);
		qw.orderByDesc("datetime");
		IPage<Share> ip= super.page(p,qw);
		return new ResultInfo(ip.getTotal(), ip.getRecords());
	}

	@Override
	public ResultInfo getSharePage(String offset, String limit) {
		Page<Share> p = new Page<>();
		p.setCurrent(Long.parseLong(offset));
		p.setSize(Long.parseLong(limit));
		QueryWrapper<Share> qw = new QueryWrapper<Share>();
		qw.orderByDesc("datetime");
		IPage<Share> ip= super.page(p,qw);
		return new ResultInfo(ip.getTotal(), ip.getRecords());
	}
	

	@Override
	public List<Share> getShareByCommentAid(String nickname) {
		return this.sm.getShareByCommentAid(nickname);
	}

	@Override
	public Integer addShareAndGetId(Share share) {
		this.sm.insert(share);
		return share.getId();
	}

}
