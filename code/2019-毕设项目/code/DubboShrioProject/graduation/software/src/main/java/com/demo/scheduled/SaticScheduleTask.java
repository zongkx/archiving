package com.demo.scheduled;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.entity.Comment;
import com.demo.entity.Software;
import com.demo.service.ICommentService;
import com.demo.service.ISoftwareService;
import com.demo.service.IUserService;

@Component
@Configuration // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class SaticScheduleTask {

	@Reference(version = "1.0.0")
	private ISoftwareService iSoftwareService;
	
	@Reference(version = "1.0.0")
	private IUserService iUserService;
	
	@Reference(version = "1.0.0")
	private ICommentService iCommentService;

	@Scheduled(fixedRate = 86400000)
	private void configureTasks() throws IOException {
		System.err.print("定时任务");
		List<Software> iList = iSoftwareService.list();
		for (Software s : iList) {
			if (StringUtils.isNoneBlank(s.getName())) {
				String name = s.getName();
				if (name.indexOf("pan.baidu.com") != -1) {
					String url = name.substring(name.indexOf("接") + 2, name.indexOf("提"));
					//if (!UrlUtils.checkUrl(url)) {// 链接失效
					if( 0 == ResourceCheckUtil.isExistResource(url, "baidu")) {
						Comment c = new Comment();
						c.setAid(s.getId());
						c.setFromUid("系统批处理");
						c.setContent("       您发布的 ==="+s.getTitle()+"=====的下载链接已经失效,请及时维护");
						c.setToUid(iUserService.getById(s.getUserId()).getNickname());
						c.setType("3");
						c.setFlag("0");
						c.setTime(LocalDateTime.now());
						iCommentService.save(c);
					}
				}
				if(name.indexOf("yunpan.360") != -1) {
					String url = name.substring(name.indexOf("接") + 2, name.indexOf("提"));
					if( 0 == ResourceCheckUtil.isExistResource(url, "360")) {
						Comment c = new Comment();
						c.setAid(s.getId());
						c.setFromUid("系统批处理");
						c.setContent("       您发布的 ==="+s.getTitle()+"=====的下载链接已经失效,请及时维护");
						c.setToUid(iUserService.getById(s.getUserId()).getNickname());
						c.setType("3");
						c.setFlag("0");
						c.setTime(LocalDateTime.now());
						iCommentService.save(c);
					}
				}
			}
		}

	}
}
