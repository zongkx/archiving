package com.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.service.UserService;

@Controller
public class JSPController {
	@Autowired
	private UserService userService;
	@RequestMapping("/boot/index")
	public  String index(Model model) {
		model.addAttribute("msg", "spring boot jsp");
		return "index";
	}
	
	@RequestMapping("/boot/Users")
	public String get(Map<String, Object> map)
	{
		map.put("list", userService.selectAll());
		
		return "list";
	}
}
