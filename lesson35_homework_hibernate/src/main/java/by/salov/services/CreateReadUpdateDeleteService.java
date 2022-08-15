package by.salov.services;

import by.salov.entity.Car;
import by.salov.entity.PassengerCar;

public interface CreateReadUpdateDeleteService {
     void create(PassengerCar passengerCar);
     Car read();
     void update(int id);
     void delete();
}
