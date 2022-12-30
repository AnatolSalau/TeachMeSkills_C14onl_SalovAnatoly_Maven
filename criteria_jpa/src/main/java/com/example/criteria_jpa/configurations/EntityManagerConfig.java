package com.example.criteria_jpa.configurations;

import com.example.criteria_jpa.entities.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class EntityManagerConfig {


    @Bean
    EntityManager entityManager() {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
                "Car"
        )) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            return entityManager;
        }
    }
}
