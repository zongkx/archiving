package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.service.ICommentService;

@RestController
@RequestMapping("/comment")
@RequiresPermissions("/comment")
public class CommentController {
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;

	@RequestMapping("/qryCommentPage")
	public ResultInfo getAllComment(@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "qryStr", required = false) String qryStr) {
		
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return  iCommentService.qryCommentPage(limit, offset, qryStr);
	}


	 

	@RequestMapping("/delComment")
	public Json delComment(@RequestParam Integer id) {
		String oper = "del  comment";
		log.info(oper);
		this.iCommentService.removeById(id);
		return Json.succ();
	}
}
