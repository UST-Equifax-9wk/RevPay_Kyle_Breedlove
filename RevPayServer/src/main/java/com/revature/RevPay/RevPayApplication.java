package com.revature.RevPay;

import com.revature.RevPay.services.EmailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.apache.logging.log4j.Logger;

@SpringBootApplication(scanBasePackages = {
 	"com.revature.RevPay.controllers",
 	"com.revature.RevPay.services",
	"com.revature.RevPay.repositories"})
public class RevPayApplication {
	public static Logger logger = LogManager.getLogger("fileLogger");
	public static void main(String[] args) {
		ApplicationContext iocContainer = SpringApplication.run(RevPayApplication.class, args);


	}

}
