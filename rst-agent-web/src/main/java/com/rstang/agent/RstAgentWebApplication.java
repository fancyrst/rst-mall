package com.rstang.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"agent-context.properties"})
public class RstAgentWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RstAgentWebApplication.class, args);
	}

}
