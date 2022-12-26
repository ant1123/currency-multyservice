package com.currency.currencyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyLoggerApplication.class, args);
	}

}
