package com.example.rest_microservice_facade_feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Turn on OpenFeign @EnableFeignClients

@SpringBootApplication
@EnableFeignClients
public class RestMicroserviceFacadeFeignclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestMicroserviceFacadeFeignclientApplication.class, args);
    }
}
