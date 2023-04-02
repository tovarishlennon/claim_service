package com.my.project.claim_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class ClaimServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimServiceApplication.class, args);
    }

}
