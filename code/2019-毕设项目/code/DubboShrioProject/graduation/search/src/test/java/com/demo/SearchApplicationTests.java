package com.demo;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.domain.ResultInfo;
import com.demo.es.entity.EsSoftware;
import com.demo.es.service.IEsSoftwareService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {

	@Autowired
	IEsSoftwareService i;

	@Test
	public void contextLoads() throws JsonParseException, JsonMappingException, IOException {

		ResultInfo r = i.searchPage(0, 10, "最受全球用户欢迎的下载工具 IDM 6 折了", "-1");
		@SuppressWarnings({ "unchecked" })
		List<EsSoftware> l = (List<EsSoftware>) r.getRows();
		for (EsSoftware es : l) {
			System.out.println(es.getId()+"xxx");
		}
	}

}
