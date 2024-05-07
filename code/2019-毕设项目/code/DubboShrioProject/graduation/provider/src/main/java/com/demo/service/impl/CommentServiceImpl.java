package com.demo.service.impl;

import com.demo.domain.ResultInfo;
import com.demo.entity.Comment;
import com.demo.mapper.CommentMapper;
import com.demo.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Raynor
 * @since 2019-04-06
 */
@com.alibaba.dubbo.config.annotation.Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
	@Autowired
	private CommentMapper cm;
	
	@Override
	public List<Comment> getComment(String aid, String type) {
		QueryWrapper<Comment> qw =new QueryWrapper<Comment>();
		qw.eq("aid", aid);
		qw.eq("type", type);
		return super.list(qw);
	}
	@Override
	public List<Comment> getAllComment(String type) {
		QueryWrapper<Comment> qw =new QueryWrapper<Comment>();
		qw.eq("type", type);
		return super.list(qw);
	}

	@Override
	public Integer AddComment(Comment comment) {
		cm.insert(comment);
		return comment.getId();
	}

	@Override
	public void delComment(Integer aid) {
		QueryWrapper<Comment> qw =new QueryWrapper<Comment>();
		qw.eq("aid", aid);
		super.remove(qw);
	}
	@Override
	public ResultInfo qryCommentPage(String limit, String offset, String content) {
		Page<Comment> page = new Page<Comment>();
		page.setSize(Integer.parseInt(limit));
		page.setCurrent(Integer.parseInt(offset));
		QueryWrapper<Comment> qw = new QueryWrapper<Comment>();
		if(StringUtils.isNotBlank(content)) {
			qw.like("content", content);
		}
		IPage<Comment> p = cm.selectPage(page, qw); 
		return new ResultInfo(p.getTotal(), p.getRecords());
	}
	@Override
	public Integer getUnreadMessage(String nickname) {
		QueryWrapper<Comment> qw = new QueryWrapper<Comment>();
		qw.eq("flag", 0);// flag = 0 未读
		qw.eq("to_uid", nickname);
		return cm.selectCount(qw);
	}
	@Override
	public ResultInfo getAllMessage(String limit,String offset,String nickname) {
		QueryWrapper<Comment> qw = new QueryWrapper<Comment>();
		qw.eq("to_uid", nickname);
		qw.orderByAsc("flag");
		qw.orderByDesc("time");
		Page<Comment> page = new Page<Comment>();
		page.setSize(Integer.parseInt(limit));
		page.setCurrent(Integer.parseInt(offset));
		IPage<Comment> p = cm.selectPage(page, qw); 
		return new ResultInfo(p.getTotal(), p.getRecords());
	}
	@Override
	public void readed(String id, String type) {
		UpdateWrapper<Comment> uw = new UpdateWrapper<Comment>();
		uw.eq("id", id);
		uw.eq("type", type);
		Comment c= new Comment();
		c.setFlag("1");
		cm.update(c, uw);
	}
	@Override
	public List<Map<Integer,Long>> getMostCommentsSoftwareId() {
		return cm.getMostCommentsSoftwareId();
	}

	public List<Comment> getMostCommentsSoftwareIds() {
		QueryWrapper<Comment> qw = new QueryWrapper<>();
		qw.groupBy("aid");
		qw.orderByDesc("count(aid)");
		qw.last(" limit 3 ");
		return cm.selectList(qw);
	}


}
