package com.authguard.authguard_client_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AuthguardClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthguardClientServiceApplication.class, args);
	}

}
