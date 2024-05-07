package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement//开启spring事务支持

//@ServletComponentScan(basePackages= {"com.demo.servlet","com.demo.filter"})

@SpringBootApplication
public class Application {
	//extends SpringBootServletInitializer

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	/*public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApplication.class);
	}*/
}
