package com.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;

@RestController
public class RestFulController {

	// http://localhost:8080/boot/restful/2
	@RequestMapping("/boot/restful/{id}")
	public Object user(@PathVariable("id") Integer id) {
		User user = new User();
		user.setId(id);
		user.setUsername("Raynor");
		return user;
	}

	// http://localhost:8080/boot/restful/2/Raynor
	@RequestMapping("/boot/restful/{id}/{name}")
	public Object user1(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		User user = new User();
		user.setId(id);
		user.setUsername(name);
		return user;
	}
}
