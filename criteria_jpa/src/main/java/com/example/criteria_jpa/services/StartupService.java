package com.example.criteria_jpa.services;

import com.example.criteria_jpa.entities.Car;
import com.example.criteria_jpa.entities.CarType;
import com.example.criteria_jpa.entities.SearchRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StartupService {

    @Autowired
    private CarJpaSpecificationService carJpaSpecificationService;

    @PostConstruct
    public void init() {
        Car car1 = new Car("CarThree", CarType.MERCEDES, new Date(),true);
        //carJpaRepository.save(car1);
        //Audi searchRequest
        SearchRequest searchRequest = SearchRequest.builder()
                .carType(CarType.BMW)
                .build();
        //Get cars from DB by SpringBOOT specification
        List<Car> allCarByCriteria = carJpaSpecificationService.findAllCarByCriteria(searchRequest);
        System.out.println("___________________Cars from search___________________________");
        System.out.println(allCarByCriteria);
        System.out.println("______________________________________________________________");
    }
}
