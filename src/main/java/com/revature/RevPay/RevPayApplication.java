package com.revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.revature.RevPay.repositories"})
//TODO add .controllers and .services back into scanBasePackages
// "com.revature.RevPay.controllers",
// "com.revature.RevPay.services",
public class RevPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevPayApplication.class, args);
	}

}
