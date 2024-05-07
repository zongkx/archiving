package com.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.demo.filter.MyFilter2;
import com.demo.servlet.MyServlet2;

@Configuration
public class ServletConfig {
	
	/*<bean id="ServletRegistrationBean" class="org.springframework.boot.web.servlet.ServletRegistrationBean">
	 * 
	 * </bean>
	 * 
	 * */
	@Bean
	public ServletRegistrationBean<MyServlet2> heServletRegistrationBean(){
		ServletRegistrationBean<MyServlet2> bean = new ServletRegistrationBean<>(new MyServlet2(),"/myservlet2");
		return bean;
		
	}
	
	
	
	
	/*<bean id="FilterRegistrationBean" class="org.springframework.boot.web.servlet.FilterRegistrationBean">
	 * 
	 * </bean>
	 * 
	 * */
	@Bean
	public FilterRegistrationBean<MyFilter2> heFilterRegistration() {
		FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean<MyFilter2>(new MyFilter2());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter encodingFilter =new CharacterEncodingFilter();
		encodingFilter.setForceEncoding(true);
		encodingFilter.setEncoding("UTF-8");
		filterRegistrationBean.setFilter(encodingFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
}
