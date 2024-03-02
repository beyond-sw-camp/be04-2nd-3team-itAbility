package com.team3.itability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ItAbilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItAbilityApplication.class, args);
    }

}
