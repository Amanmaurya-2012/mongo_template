package com.example.mongoTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MongoTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoTemplateApplication.class, args);
	}

}
