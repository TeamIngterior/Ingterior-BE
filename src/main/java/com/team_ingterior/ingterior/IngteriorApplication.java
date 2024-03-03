package com.team_ingterior.ingterior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.team_ingterior.*")
public class IngteriorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngteriorApplication.class, args);
	}

}
	