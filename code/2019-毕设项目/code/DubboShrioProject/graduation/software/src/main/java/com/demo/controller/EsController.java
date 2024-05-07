package com.demo.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.ResultInfo;
import com.demo.es.service.IEsSoftwareService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/es")
public class EsController {
	private static final Logger log = LoggerFactory.getLogger(EsController.class);

	@Reference(version = "1.0.0")
	private IEsSoftwareService iEsSoftwareService;

	@RequestMapping("/qrySoftware")
	public ResultInfo qry(@RequestParam(value = "qry", required = true) String qry,
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "limit", required = false) String limit,
			@RequestParam(value = "softwareId", required = false) String softwareId)
					throws JsonParseException, JsonMappingException, NumberFormatException, IOException {
		log.info("qry  : " + qry);
		if (!StringUtils.isNotBlank(limit) & !StringUtils.isNotBlank(offset)) {
			log.info("初始化页面信息");
			offset = "0";
			limit = "10";
		}
		if(!StringUtils.isNotBlank(softwareId)) {//如果为空 则表明该用户行为记录并未产生与之相似度高的用户所计算出来的值
			softwareId = "-1";
		}
		ResultInfo r = iEsSoftwareService.searchPage(Integer.parseInt(offset), Integer.parseInt(limit), qry,softwareId);
		return r;
	}

}
