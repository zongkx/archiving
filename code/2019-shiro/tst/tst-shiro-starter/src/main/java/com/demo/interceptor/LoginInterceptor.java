package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		Subject subject=SecurityUtils.getSubject();
		//.getAttribute("")
//		if(subject!=null) 
//		  { 
//			  return true; 
//		  }
//		  //返回为真则可以通过,返回为false 
//		  response.sendRedirect("http://localhost:8080/index"); 
		  return true;
		 
	}
}