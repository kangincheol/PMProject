package com.example.pmproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PmProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmProjectApplication.class, args);
    }

}
