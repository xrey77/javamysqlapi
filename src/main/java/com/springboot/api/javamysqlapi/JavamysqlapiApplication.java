package com.springboot.api.javamysqlapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.springboot.api.javamysqlapi")
@SpringBootApplication
public class JavamysqlapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavamysqlapiApplication.class, args);
	}

}
