package com.example.criteria_jpa.services;

import com.example.criteria_jpa.entities.Car;
import com.example.criteria_jpa.repository.CarJpaRepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarJpaSpecificationService {
    @Autowired
    CarJpaRepositoryImpl carJpaRepository;

}
