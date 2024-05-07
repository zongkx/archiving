package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.config.ConfigInfo;

@Controller
public class ConfigController {
	
	@Autowired
	private ConfigInfo configInfo; 
	
	@RequestMapping("/boot/config")
	public @ResponseBody String hello() {
		return configInfo.getName()+configInfo.getAge();
	}
}
