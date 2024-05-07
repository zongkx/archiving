package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

@EnableTransactionManagement//开启spring事务支持
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
