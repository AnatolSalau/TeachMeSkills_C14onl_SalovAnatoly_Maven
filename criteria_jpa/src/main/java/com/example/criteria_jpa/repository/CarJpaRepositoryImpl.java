package com.example.criteria_jpa.repository;

import com.example.criteria_jpa.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//Repository with specifications - criteria like Hibernate
public interface CarJpaRepositoryImpl extends JpaRepository<Car,Integer>,
        JpaSpecificationExecutor<Car> {

}
