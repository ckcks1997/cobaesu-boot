package com.example.cobaesuboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CobaesuBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobaesuBootApplication.class, args);
    }

}
