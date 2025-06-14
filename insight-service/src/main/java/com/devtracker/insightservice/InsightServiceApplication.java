package com.devtracker.insightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InsightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsightServiceApplication.class, args);
	}

}
