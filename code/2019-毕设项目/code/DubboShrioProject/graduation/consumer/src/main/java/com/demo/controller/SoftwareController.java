package com.demo.controller;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.domain.ResultInfo;
import com.demo.entity.Software;
import com.demo.service.ICommentService;
//import com.demo.respository.UserRespostory;
import com.demo.service.ISoftwareService;
import com.demo.utils.HtmlUtils;

@RestController
@RequestMapping("/software")
public class SoftwareController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Reference(version="1.0.0")
	private ISoftwareService iSoftwareService;
	
	@Reference(version="1.0.0")
	private ICommentService iCommentService;
	
	
	@RequiresPermissions("/allSoftware")
	@RequestMapping("/getSoftwarePage")
	public @ResponseBody ResultInfo getSoftwarePage(  @RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "qryStr", required = false) String qryStr){
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return  iSoftwareService.getSoftwarePageByTitle(offset, limit, qryStr);
	}
	@RequiresPermissions("/oneSoftware")
	@RequestMapping("/getSoftwarePageByAid")
	public @ResponseBody ResultInfo getSoftwarePageByAid(  @RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "qryStr", required = false) String qryStr,
			@RequestParam(value = "aid", required = false) String aid){
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return  iSoftwareService.getSoftwarePageByTitleAndAid(offset, limit, qryStr,aid);
	}
	@RequestMapping("/getSoftwarePageByType")
	public @ResponseBody ResultInfo getSoftwarePageByType(  @RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "type", required = false) String type) {
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		return  this.iSoftwareService.getSoftwarePageByType(offset, limit, type);
		
	}
	@RequestMapping("/addSoftware")
	public @ResponseBody Json addS( @RequestBody Software software) {
		software.setDate(LocalDateTime.now());
		software.setScore(0L);
		String pic = HtmlUtils.getImgStrOne(software.getText());
		String style= " style='height:inherit;width:100% ' ";
		String pic0 = "<img  src='"+pic+"'"+style+">";
		software.setPhoto(pic0);
		this.iSoftwareService.save(software);
		return  Json.succ();
		
	}
	@RequestMapping("/modifySoftware")
	public @ResponseBody Json modifySf( @RequestBody Software software) {
		software.setDate(LocalDateTime.now());
		String pic = HtmlUtils.getImgStrOne(software.getText());
		String style= " style='height:inherit;width:100% ' ";
		String pic0 = "<img  src='"+pic+"'"+style+">";
		software.setPhoto(pic0);
		this.iSoftwareService.updateById(software);
		return  Json.succ();
		
	}
	
	
	@RequestMapping("/getSoftwareAllType")
	public @ResponseBody Json getSoftwareAllType() {
		log.info( "getSoftwareAllType ");
		return  Json.succ().data(this.iSoftwareService.getSfAlltype());
	}
	
	@RequestMapping("/delSoftwareById")
	public @ResponseBody Json delSoftware(@RequestParam(value = "id", required = false) String id) {
		String oper = "del Software";
		this.iSoftwareService.removeById(id);
		this.iCommentService.delComment(Integer.parseInt(id));
		return Json.succ(oper);
	}
	
	

}
