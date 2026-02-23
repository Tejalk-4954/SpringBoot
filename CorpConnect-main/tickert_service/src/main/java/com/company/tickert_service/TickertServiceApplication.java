package com.company.tickert_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class TickertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickertServiceApplication.class, args);
		System.out.println("ticket service...");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}


//set MINIO_ROOT_USER=minio-access-key
//set MINIO_ROOT_PASSWORD=minio-secret-key
//minio.exe server data --console-address ":9001"

//bin\windows\kafka-server-start.bat config\server.properties
