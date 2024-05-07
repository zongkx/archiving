package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.demo.mapper")//扫描mapper接口对应的包,也可以选择在每个mapper.java类前面加上@Mapper注解
public class TstMpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TstMpApplication.class, args);
		
	}

}

