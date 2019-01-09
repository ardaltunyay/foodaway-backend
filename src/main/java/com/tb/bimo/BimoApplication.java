package com.tb.bimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BimoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BimoApplication.class, args);
    }
}
