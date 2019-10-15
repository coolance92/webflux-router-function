package com.coolance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @author Coolance
 */
@SpringBootApplication
public class WebfluxRouterFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxRouterFunctionApplication.class, args);
	}

}
