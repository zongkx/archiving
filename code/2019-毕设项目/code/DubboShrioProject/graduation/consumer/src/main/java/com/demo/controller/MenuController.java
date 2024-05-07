package com.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Json;

@RestController
@RequestMapping("/menu")
public class MenuController {

	
	@RequestMapping("/check")
	public Json check(@RequestParam(value = "perm", required = false) String perm) {
		
		List<String> list = Arrays.asList(perm.split(","));
		List<Boolean> rList = new ArrayList<Boolean>();
		for(String s :list) {
			boolean flag ;
			if("/software".equals(s)) {
				if(SecurityUtils.getSubject().isPermitted("/allSoftware") || SecurityUtils.getSubject().isPermitted("/oneSoftware")) {
					rList.add(true);	
				}
			}else {
				flag = SecurityUtils.getSubject().isPermitted(s);
				rList.add(flag);
			}
			
		}
		return Json.succ().data(rList);
	}
	
}
