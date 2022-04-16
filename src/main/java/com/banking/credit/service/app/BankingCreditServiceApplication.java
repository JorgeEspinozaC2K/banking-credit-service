package com.banking.credit.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankingCreditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCreditServiceApplication.class, args);
	}

}
