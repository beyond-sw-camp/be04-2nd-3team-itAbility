package com.team3.itabilityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ItabilityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItabilityServerApplication.class, args);
    }

}
