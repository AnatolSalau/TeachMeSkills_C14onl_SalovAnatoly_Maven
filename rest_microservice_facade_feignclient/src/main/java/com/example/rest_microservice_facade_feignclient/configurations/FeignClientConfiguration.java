package com.example.rest_microservice_facade_feignclient.configurations;

import com.example.rest_microservice_facade_feignclient.handlers.CustomUserErrorDecoderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomUserErrorDecoderImpl();
    }
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
