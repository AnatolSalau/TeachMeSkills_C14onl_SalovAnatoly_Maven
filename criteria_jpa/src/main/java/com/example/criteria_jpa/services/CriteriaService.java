package com.example.criteria_jpa.services;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        System.out.println("----------------------------------");
        System.out.println(entityManager);
        System.out.println("----------------------------------");
    }
}
