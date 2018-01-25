package com.rstang.support;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.rstang")
@PropertySource({"classpath:common-context.properties"})
public class CommonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonWebApplication.class, args);
	}
}
