//package com.demo.scheduled;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.demo.es.entity.EsSoftware;
//import com.demo.es.service.IEsSoftwareService;
//
//@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
//public class SaticScheduleTask {
//	
//	@Reference(version = "1.0.0")
//	private IEsSoftwareService iSoftwareEsService;
//	
//	@Reference(version = "1.0.0")
//	private IEsSoftwareService iEsSoftwareService;
//    //3.添加定时任务
////    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔 
//    @Scheduled(fixedRate=50000000)
//    private void configureTasks() {
//    	List<EsSoftware> list = iEsSoftwareService.list();
//		this.iSoftwareEsService.saveAll(list);
//    	System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
//}
