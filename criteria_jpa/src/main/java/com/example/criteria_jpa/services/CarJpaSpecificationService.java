package com.example.criteria_jpa.services;

import com.example.criteria_jpa.entities.Car;
import com.example.criteria_jpa.entities.SearchRequest;
import com.example.criteria_jpa.repository.CarJpaRepositoryImpl;
import com.example.criteria_jpa.specifications.CarSpecification;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarJpaSpecificationService {
    @Autowired
    CarJpaRepositoryImpl carJpaRepository;

    /**
     * Get cars from DB by SpringBOOT specification
     */
    List<Car> findAllCarByCriteria(SearchRequest searchRequest) {
        Specification<Car> carSpecification = CarSpecification.getCarSpecification(searchRequest);
        List<Car> all = carJpaRepository.findAll(carSpecification);
        return all;
    }

    public void save(Car car) {
        carJpaRepository.save(car);
    }
}
