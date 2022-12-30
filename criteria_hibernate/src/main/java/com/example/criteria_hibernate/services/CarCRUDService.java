package com.example.criteria_hibernate.services;

import com.example.criteria_hibernate.entity.Car;
import com.example.criteria_hibernate.entity.CarType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CarCRUDService {

    @Autowired
    private  SessionFactory sessionFactory;

    @Autowired
    public CarCRUDService() {
    }
    @PostConstruct
    private void init() {
        System.out.println("______________________________________________________________");
        System.out.println(sessionFactory.toString());
        saveCar();
        System.out.println("______________________________________________________________");
    }

    private void saveCar() {
        Car car = new Car("CarOne", CarType.AUDI, new Date(), true);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(car);
        transaction.commit();
        session.close();
    }
}
