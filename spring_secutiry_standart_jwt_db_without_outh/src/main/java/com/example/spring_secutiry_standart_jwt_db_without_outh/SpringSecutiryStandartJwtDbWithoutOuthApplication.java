package com.example.spring_secutiry_standart_jwt_db_without_outh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableJpaRepositories(basePackages = {"com.example.spring_secutiry_standart_jwt_db_without_outh.repository"})
@EntityScan("com.example.spring_secutiry_standart_jwt_db_without_outh")
@SpringBootApplication(scanBasePackages = {"com.example.spring_secutiry_standart_jwt_db_without_outh",
        "com.example.spring_secutiry_standart_jwt_db_without_outh.repository"} )

public class SpringSecutiryStandartJwtDbWithoutOuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecutiryStandartJwtDbWithoutOuthApplication.class, args);
    }

}
