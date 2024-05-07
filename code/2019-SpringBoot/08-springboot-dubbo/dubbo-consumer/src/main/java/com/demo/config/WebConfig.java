package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	 

	public void addInterceptors(InterceptorRegistry registry) {
		String [] PathPatterns = {
				"/**"
		};
		String [] exPathPatterns = {
				"/js/**","/login","/2register","/webjars/**","/checkAccount"
		};
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns(PathPatterns).excludePathPatterns(exPathPatterns);
	
	}
	
	
}
