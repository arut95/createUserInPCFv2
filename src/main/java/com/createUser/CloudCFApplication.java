package com.createUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CloudCFApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudCFApplication.class, args);
	}
}
