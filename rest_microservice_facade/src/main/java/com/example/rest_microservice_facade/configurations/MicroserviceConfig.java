package com.example.rest_microservice_facade.configurations;

import com.example.rest_microservice_facade.handlers.RestTemplateErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for link with other microservices
 */
@Configuration
public class MicroserviceConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate build = restTemplateBuilder
                .errorHandler(new RestTemplateErrorHandler())
                .build();
        return build;
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
