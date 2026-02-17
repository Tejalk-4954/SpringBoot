package com.company.hiring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {
	    KafkaAutoConfiguration.class
	})
@EnableDiscoveryClient
public class HiringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiringServiceApplication.class, args);
		System.out.println("hirng service...");
	
	}
//	 @Bean
//	    public RestTemplate restTemplate() {
//	        return new RestTemplate();
//	    }
//	 

}
