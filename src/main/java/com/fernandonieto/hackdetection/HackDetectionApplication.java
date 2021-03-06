package com.fernandonieto.hackdetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.fernandonieto.hackdetection.infrastructure")
public class HackDetectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackDetectionApplication.class, args);
    }

}
