package com.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SearchApplication {

	public static void main(String[] args) {
		 new SpringApplicationBuilder(SearchApplication.class)
         .web(WebApplicationType.NONE)
         .run(args);
		 }
}

