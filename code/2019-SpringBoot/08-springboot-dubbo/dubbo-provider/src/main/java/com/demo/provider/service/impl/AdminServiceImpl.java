package com.demo.provider.service.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.AdminService;

@Component

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class AdminServiceImpl implements AdminService{
	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return "hello dubbo (admin)";
	}
	
}

