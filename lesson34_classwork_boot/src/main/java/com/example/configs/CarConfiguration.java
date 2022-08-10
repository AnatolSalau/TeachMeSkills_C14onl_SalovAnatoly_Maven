package com.example.configs;

import com.example.services.CarService;
import com.example.services.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//This configuration will create if bean CarService is
@ConditionalOnBean(value = UserService.class)
//This configuration will create if property user.service.enable is in application.yaml
/*user:
        service:
        enable: true;*/
@ConditionalOnProperty(name = "user.service.enable")
public class CarConfiguration {
    @Bean
    CarService carService() {
        System.out.println("carService");
        return new CarService();
    }
}
