package com.in.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReportGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportGenerationApplication.class, args);
	}

}
