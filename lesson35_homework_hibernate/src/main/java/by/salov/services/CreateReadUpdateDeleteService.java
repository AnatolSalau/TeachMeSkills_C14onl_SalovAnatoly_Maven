package by.salov.services;

import by.salov.entity.Car;
import by.salov.entity.CarType;
import by.salov.entity.PassengerCar;

import java.util.List;

public interface CreateReadUpdateDeleteService {
     void create(PassengerCar passengerCar);
     Car read();
     void update(int id, int quantityPeople, CarType carType, java.util.Date dateCreationCar, boolean hasCar);
     void delete();
     List<PassengerCar> getAllCars();
}
