package com.example.webstoreorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.example.webstoreorderservice.serviceproxy")
public class WebStoreOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStoreOrderServiceApplication.class, args);
	}
}
