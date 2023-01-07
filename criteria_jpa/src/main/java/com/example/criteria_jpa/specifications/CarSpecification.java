package com.example.criteria_jpa.specifications;

import com.example.criteria_jpa.entities.Car;
import com.example.criteria_jpa.entities.CarType;
import com.example.criteria_jpa.entities.SearchRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CarSpecification {
    public static final Specification<Car> getCarSpecification(SearchRequest request) {
       return (root, query, builder) -> {
           String name = request.getName();
           CarType carType = request.getCarType();
           boolean hasCar = request.getHasCar();

           List<Predicate> predicateList = new ArrayList<>();

           // name - field name from Car
           if(name != null) {
               //if root field "name" equal field "name" in request
               Predicate namePredicate = builder.equal(root.get("name"), name);
               predicateList.add(namePredicate);
           }
           if(carType != null) {
               Predicate carTypePredicate = builder.equal(root.get("carType"), carType);
               predicateList.add(carTypePredicate);
           }
           if(carType != null) {
               Predicate isHasCarPredicate = builder.equal(root.get("hasCar"),hasCar);
               predicateList.add(isHasCarPredicate);
           }
       }
    }
}
