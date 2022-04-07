package com.example.myRetail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class MyRetailApplication {

	static void main(String[] args) {
		SpringApplication.run(MyRetailApplication, args)
	}

}
