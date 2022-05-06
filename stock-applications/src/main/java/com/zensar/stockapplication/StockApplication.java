package com.zensar.stockapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.zensar.stockapplication.entity.Stock;

@SpringBootApplication
//@ImportResource("Beans.xml")
public class StockApplication{
	
	
	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
		
		
		Stock stock = new Stock();
		stock.getStockId();
		stock.getName();
		stock.getMarketName();
		stock.getPrice();
		
		
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
