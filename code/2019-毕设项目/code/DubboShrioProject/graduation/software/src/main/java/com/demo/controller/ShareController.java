package com.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.entity.Share;
import com.demo.service.ICommentService;
import com.demo.service.IShareService;
import com.demo.service.IUserService;

@RestController
@RequestMapping("/share")
public class ShareController {
	private static final Logger log = LoggerFactory.getLogger(ShareController.class);

	@Reference(version = "1.0.0")
	private IShareService iShareService;
	
	@Reference(version = "1.0.0")
	private IUserService iUserService;
	
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;

	@RequestMapping(value = { "/addShare" }, method = { RequestMethod.POST }, consumes = {
			"application/json" }, produces = "application/json;charset=UTF-8")
	public Json addShare(@RequestBody Share share) {// 新增动态
		String oper = "change User Info";
		log.info(oper + share);
		Integer id =iShareService.addShareAndGetId(share);
		share.setId(id);
		return Json.succ(oper).data(share);

	}

	@RequestMapping(value = { "/getSharePageByUserId" })
	public  @ResponseBody ResultInfo getSharePageByUserId(@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "userId", required = false) String userId) {// 动态分页信息
		String oper = "SharePage";
		log.info(oper + "");
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return this.iShareService.getSharePageByUserId(offset, limit, userId);

	}
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
		return this.iShareService.getSharePage(offset, limit);

	}
	@RequestMapping(value = { "/deleteShareById" })
	public  @ResponseBody Json del(@RequestParam(value = "id", required = false) String id) {// 动态分页信息
		String oper = "del share";
		log.info(oper + "");
		this.iShareService.removeById(id);
		this.iCommentService.delComment(Integer.parseInt(id));
		return Json.succ(oper);

	}
	@RequestMapping(value = { "/getNicknameByUserId" })
	public  @ResponseBody Json getNicknameByUserId(@RequestParam(value = "userId", required = false) String userId) {// 动态分页信息
		String oper = "get User  Nickname";
		log.info(oper + "");
		return Json.succ(oper).data(this.iUserService.getById(userId).getNickname());

	}
	@RequestMapping(value = { "/likeIt" })
	public  @ResponseBody Json likeIt(@RequestParam(value = "itemId", required = false) String itemId,
			@RequestParam(value = "likenum", required = false) String likenum,
			@RequestParam(value = "nickname", required = false) String nickname) {// 动态分页信息
		String oper = "like it";
		log.info(oper + "");
		Share share = this.iShareService.getById(itemId);
		String nicknames = share.getLikeId();
		log.info(nicknames);
		boolean flag = true;
		if(StringUtils.isNotBlank(nicknames)) {
			for(String s :StringUtils.split(nicknames, ",")) {
				if(nickname.equals(s)) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				return Json.fail(oper);
			}else {
				share.setLikeId(StringUtils.join(nicknames, ","+nickname));
				share.setLikenum(share.getLikenum()+1);
				this.iShareService.updateById(share);
				return Json.succ();
			}
		}else{
			share.setLikeId(StringUtils.join(nicknames,nickname));
			share.setLikenum(share.getLikenum()+1);
			this.iShareService.updateById(share);
			return Json.succ(oper);
		}
		

	}
	
	@RequestMapping("/getMyInfoByCommentAid")
	public @ResponseBody Json getMyInfo(@RequestParam(value = "userId", required = false) String userId) {
		
		return Json.succ().data(this.iShareService.getShareByCommentAid(userId));
	}
	
	@RequestMapping(value = { "/getShareById" })
	public @ResponseBody Json getSharePageByUserIdSort(@RequestParam(value = "aid", required = false) String aid) {// 动态分页信息
		String oper = "SharePage";
		log.info(oper + "");
		 
		return Json.succ().data(this.iShareService.getById(aid));

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
