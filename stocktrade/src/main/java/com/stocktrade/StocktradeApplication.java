package com.stocktrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication

@OpenAPIDefinition(info = @Info(title = "Portfolio Tracking API (Stock market)", version = "1.0", description = "Portfolio Trading"))
public class StocktradeApplication {
	public static void main(String[] args) {
		SpringApplication.run(StocktradeApplication.class, args);
	}

}
