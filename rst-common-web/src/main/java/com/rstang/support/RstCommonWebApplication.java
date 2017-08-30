package com.rstang.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"common-context.properties"})
public class RstCommonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RstCommonWebApplication.class, args);
	}
}
