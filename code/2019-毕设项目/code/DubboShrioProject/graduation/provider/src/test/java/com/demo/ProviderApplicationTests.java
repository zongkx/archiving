package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.entity.Software;
import com.demo.service.ISoftwareService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderApplicationTests {
	
	@Autowired
	private ISoftwareService o;
	
	@Test
	public void test() {
		Page<Software> p= new Page<>();
		p.setCurrent(1);
		p.setSize(5);
		
		QueryWrapper<Software> q = new QueryWrapper<>();
		q.eq("type", "windows");
		
		System.out.println(o.page(p,q).getRecords()+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");;
		
		
	}

}
