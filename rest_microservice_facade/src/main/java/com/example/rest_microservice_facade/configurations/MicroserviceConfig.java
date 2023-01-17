package com.example.rest_microservice_facade.configurations;

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
        return new RestTemplate();
    }
}