package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.domain.Json;
import com.demo.es.entity.EsSoftware;
import com.demo.es.service.IEsSoftwareService;

@RestController
@RequestMapping("/es")
public class EsController {
	private static final Logger log = LoggerFactory.getLogger(EsController.class);

	@Reference(version = "1.0.0")
	private IEsSoftwareService iSoftwareEsService;
	
	@Reference(version = "1.0.0")
	private IEsSoftwareService iEsSoftwareService;
	
	
	@RequestMapping("/saveSoftwares")
	public  Json saveUsers() {
		log.info(" save softwares");
		List<EsSoftware> list = iEsSoftwareService.list();
		this.iSoftwareEsService.saveAll(list);
		return Json.succ();
	}
	 
}
