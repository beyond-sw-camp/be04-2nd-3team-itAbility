package com.team3.memberservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "IT-Ability",
                description = "건들지 말 것.",
                version = "1.0"))
@Configuration
public class SwaggerConfig {
}