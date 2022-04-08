package com.example.myRetail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableMongoRepositories
class MyRetailApplication {

	static void main(String[] args) {
		SpringApplication.run(MyRetailApplication, args)
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build()
	}
}
