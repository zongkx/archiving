package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.service.ICommentService;
import com.demo.service.IShareService;

@RestController
@RequestMapping("/share")
@RequiresPermissions("/share")
public class ShareController {
	private static final Logger log = LoggerFactory.getLogger(ShareController.class);

	@Reference(version = "1.0.0")
	private IShareService iShareService;
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;
	
	@RequestMapping(value = { "/getSharePage" })
	public  @ResponseBody ResultInfo getSharePage(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit) {// 动态分页信息
		String oper = "SharePage";
		log.info(oper + "");
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		ResultInfo r=  this.iShareService.getSharePage(offset, limit);
		return r;
	}
	@RequestMapping(value = { "/deleteShareById" })
	public  @ResponseBody Json del(@RequestParam(value = "id", required = false) String id) {// 动态分页信息
		String oper = "del share";
		log.info(oper + "");
		this.iShareService.removeById(id);
		this.iCommentService.delComment(Integer.parseInt(id));
		return Json.succ(oper);

	}
	
	
	
}
