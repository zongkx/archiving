package com.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.Comment;
import com.demo.entity.Software;
import com.demo.service.ICommentService;
import com.demo.service.ISoftwareService;
import com.demo.utils.UrlUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareApplicationTests {
	@Reference(version = "1.0.0")
	private ISoftwareService iSoftwareService;

	@Reference(version = "1.0.0")
	private ICommentService iCommentService;
	@Test
	public void contextLoads() throws IOException {
		
		List<Software> iList = iSoftwareService.list();
		for (Software s : iList) {
			if (StringUtils.isNoneBlank(s.getName())) {
				String name = s.getName();
				if (name.indexOf("pan.baidu.com") != -1) {
					String url = name.substring(name.indexOf("接") + 2, name.indexOf("提"));
					if (!UrlUtils.checkUrl(url)) {// 链接失效
						Comment c = new Comment();
						c.setAid(s.getId());
						c.setContent("您发布的"+s.getTitle()+"的下载链接已经失效,请及时维护");
						c.setToUid(s.getUserId().toString());
						c.setType("3");
						c.setFlag("0");
						c.setTime(LocalDateTime.now());
						iCommentService.save(c);
					}
				}
			}
		}
	}
	
//	@Test 
//	public void test() {
//		 System.out.println(ResourceCheckUtil.isExistResource("http://pan.baidu.com/s/1jGjBmyq",
//		         "baidu"));
//	}

}
