package com.example.spring_rest.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for separation api in swagger
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi userOpenApi() {
        String paths[] = {"/api/user/**"};
        return GroupedOpenApi
                .builder()
                .group("My users")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi exceptionOpenApi() {
        String paths[] = {"/api/exception/**"};
        return GroupedOpenApi
                .builder()
                .group("My exceptions")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi actuatorOpenApi() {
        String paths[] = {"/actuator/**"};
        return GroupedOpenApi
                .builder()
                .group("Actuator")
                .pathsToMatch(paths)
                .build();
    }
}
