package com.demo.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.demo.interceptor.LoginInterceptor;
//@Configuration
public class WebConfig_bck extends WebMvcConfigurationSupport {
	 
	@Override
    protected void addInterceptors(InterceptorRegistry registry) {
        
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("");
        
    }
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/index").setViewName("index");
    } 
	
}
