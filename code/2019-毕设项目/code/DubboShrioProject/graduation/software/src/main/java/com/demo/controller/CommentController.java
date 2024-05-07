package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.entity.Comment;
import com.demo.service.ICommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;

	/*
	 * 鉴于评论的样本不大, ====本系统不区分评论和回复，统一看成评论=== 用户点击显示评论的按钮后, 根据用户传递过来的,
	 * 关联ID和Type(type为1 则为关联 软件表, type为2则关联动态表), 其中to_uid 为空的行,即为用户对软件/动态的评论 to_uid
	 * 不为空的 行,即为用户 @目标用户 的评论
	 * 
	 */
	@RequestMapping("/getCommentByTypeAndId")
	public Json getMessage(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "type", required = false) String type) {
		String oper = "get Message";
		log.info(oper);
		return Json.succ().data(this.iCommentService.getComment(id, type));
	}

	@RequestMapping("/getCommentByType")
	public Json getAllComment(@RequestParam(value = "type", required = false) String type) {
		String oper = "get Message";
		log.info(oper);
		return Json.succ().data(this.iCommentService.getAllComment(type));
	}

	@RequestMapping("/addComment")
	public Json addComment(@RequestBody Comment c) {
		String oper = "add Comment";
		if (c.getContent().startsWith("@")) {
			int leng = c.getContent().indexOf(" ");
			int last = c.getContent().length();
			String toNickname = c.getContent().substring(1, leng);
			@SuppressWarnings("unused")
			String content = c.getContent().substring(leng + 3, last);
			c.setToUid(toNickname);
		}

		log.info(oper);
		Integer id = this.iCommentService.AddComment(c);
		return Json.succ().data(id);
	}

	@RequestMapping("/delComment")
	public Json delComment(@RequestBody Integer id) {
		String oper = "del  comment";
		log.info(oper);
		this.iCommentService.removeById(id);
		return Json.succ();
	}

	// 获取未读消息总数
	@RequestMapping("/getUnreadMessageCount")
	public Json getCount(@RequestParam(value = "nickname", required = false) String nickname) {
		return Json.succ().data(iCommentService.getUnreadMessage(nickname));
	};

	// 获取所有消息总数
	@RequestMapping("/getMessageByNickname")
	public Json getAllMessage(@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return Json.succ().data(iCommentService.getAllMessage(limit, offset, nickname));
	};

	// 获取所有消息总数
	@RequestMapping("/readed")
	public Json readed(@RequestParam(value = "id", required = false) String id) {
		Comment c = new Comment();
		c.setId(Integer.parseInt(id));
		c.setFlag("1");
		iCommentService.updateById(c);
		return Json.succ();
	};

}
