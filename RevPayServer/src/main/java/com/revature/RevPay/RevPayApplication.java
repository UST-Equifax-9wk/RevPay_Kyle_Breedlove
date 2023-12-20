package com.revature.RevPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
 	"com.revature.RevPay.controllers",
 	"com.revature.RevPay.services",
	"com.revature.RevPay.repositories"})
public class RevPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevPayApplication.class, args);
	}

}
