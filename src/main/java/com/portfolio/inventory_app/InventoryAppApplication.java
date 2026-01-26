package com.portfolio.inventory_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class InventoryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryAppApplication.class, args);
	}

}
